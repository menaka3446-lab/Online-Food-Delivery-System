package com.menakasoft.foodiex.features.cart;

import com.menakasoft.foodiex.data.dto.Cart;
import com.menakasoft.foodiex.data.dto.FoodItem;

import java.util.List;

public class CartPresenter {
    private final CartView view;
    private final CartModel model;

    public CartPresenter(CartView view) {
        this.view = view;
        this.model = new CartModel();
    }

    public void startCart() {
        Cart cart = model.getOrCreateCart(view.getUser().getId(), (int) view.getRestaurant().getId());
        view.showCartMenu(cart);
    }

    public void addItems(Cart cart) {
        List<FoodItem> menu = view.getMenu();
        int idx = view.pickFoodItem();
        if (idx < 0 || idx >= menu.size()) { view.showMessage("Invalid selection."); return; }
        int qty = view.pickQuantity();
        if (qty <= 0) { view.showMessage("Invalid quantity."); return; }
        Cart updated = model.addToCart(cart, menu.get(idx), qty);  // ← return value பிடி
        cart.setItems(updated.getItems());                          // ← cart update பண்ணு
        cart.setSubTotal(updated.getSubTotal());
        cart.setTax(updated.getTax());
        cart.setDiscount(updated.getDiscount());
        cart.setDeliveryCharge(updated.getDeliveryCharge());
        cart.setTotalAmount(updated.getTotalAmount());
        view.showMessage(menu.get(idx).getName() + " added to cart.");

    }

    public void removeItem(Cart cart) {
        if (cart.getItems().isEmpty()) { view.showMessage("Cart is empty."); return; }
        int idx = view.pickRemoveIndex(cart.getItems().size());
        if (idx < 0 || idx >= cart.getItems().size()) { view.showMessage("Invalid selection."); return; }
        String name = cart.getItems().get(idx).getFoodName();
        Cart updated = model.removeItem(cart, idx);                 // ← return value பிடி
        cart.setItems(updated.getItems());                          // ← cart update பண்ணு
        cart.setSubTotal(updated.getSubTotal());
        cart.setTax(updated.getTax());
        cart.setDiscount(updated.getDiscount());
        cart.setDeliveryCharge(updated.getDeliveryCharge());
        cart.setTotalAmount(updated.getTotalAmount());
        view.showMessage(name + " removed from cart.");
    }
}
