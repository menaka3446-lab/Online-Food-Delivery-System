//package com.menakasoft.foodiex.features.cart;
//
//import com.menakasoft.foodiex.data.dto.Cart;
//import com.menakasoft.foodiex.data.dto.CartItem;
//import com.menakasoft.foodiex.data.dto.FoodItem;
//import com.menakasoft.foodiex.data.repositary.FoodieXDB;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartModel {

//    Cart getOrCreateCart(Long userId, int restaurantId){
//        Cart cart= FoodieXDB.getInstance().getCartByUserId(userId);
//        if(cart==null){
//            cart= new Cart();
//            cart.setUserId(userId);
//            cart.setRestaurantId(restaurantId);
//        }
//        return cart;
//    }
//
//    Cart addToCart(Cart cart, FoodItem item,int quantity){
//        List<CartItem> items=cart.getItems();
//        if(items==null) items=new ArrayList<>();
//
//        for(CartItem cartItem:items){
//            if(cartItem.getFoodId().equals(item.getId())){
//                cartItem.setQuantity(cartItem.getQuantity()+quantity);
//                cart.setItems(items);
//                return reCalculate(cart);
//            }
//        }
//        CartItem cartItem=new CartItem();
//        cartItem.setFoodId(item.getId());
//        cartItem.setFoodName((item.getName()));
//        cartItem.setPrice(item);
//    }
//}
