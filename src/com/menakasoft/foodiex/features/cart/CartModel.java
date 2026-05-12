package com.menakasoft.foodiex.features.cart;

import com.menakasoft.foodiex.data.dto.Cart;
import com.menakasoft.foodiex.data.dto.CartItem;
import com.menakasoft.foodiex.data.dto.FoodItem;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;
import com.menakasoft.foodiex.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class CartModel {

    Cart getOrCreateCart(Long userId, int restaurantId){
        Cart cart= FoodieXDB.getInstance().getCartByUserId(userId);
        if(cart==null){
            cart= new Cart();
            cart.setUserId(userId);
            cart.setRestaurantId(restaurantId);
            cart.setItems(new ArrayList<>());
        }
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());  // ← safety check
        }
        return cart;
    }

    Cart addToCart(Cart cart, FoodItem item,int quantity){
        List<CartItem> items=cart.getItems();
        if(items==null) items=new ArrayList<>();
     // Check if item already exists
        for(CartItem cartItem:items){
            if(cartItem.getFoodId().equals(item.getId())){
                cartItem.setQuantity(cartItem.getQuantity()+quantity);
                cart.setItems(items);
                return reCalculate(cart);
            }
        }
        CartItem cartItem=new CartItem();
        cartItem.setFoodId(item.getId());
        cartItem.setFoodName((item.getName()));
        cartItem.setPrice(item.getPrice());
        cartItem.setQuantity(quantity);
        items.add(cartItem);
        cart.setItems(items);
        return reCalculate(cart);
    }

    Cart reCalculate(Cart cart) {
        double sub = 0;
        for (CartItem ci : cart.getItems()) sub += ci.getTotal();
        double tax      = sub * Constants.TAX_RATE;
        double discount = sub * Constants.DISCOUNT_RATE;
        double delivery = sub > 0 ? Constants.DELIVERY_CHARGE : 0;
        cart.setSubTotal(sub);
        cart.setTax(tax);
        cart.setDiscount(discount);
        cart.setDeliveryCharge(delivery);
        cart.setTotalAmount(sub + tax - discount + delivery);
        cart.setTotalItems(cart.getItems().stream().mapToInt(CartItem::getQuantity).sum()); // ← fix
        FoodieXDB.getInstance().saveCart(cart);
        return cart;
    }
    Cart removeItem(Cart cart, int index) {
        List<CartItem> items = cart.getItems();
        if (index >= 0 && index < items.size()) items.remove(index);
        cart.setItems(items);
        return reCalculate(cart);
    }

}
