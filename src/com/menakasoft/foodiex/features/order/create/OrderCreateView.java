package com.menakasoft.foodiex.features.order.create;

import com.menakasoft.foodiex.data.dto.*;
import com.menakasoft.foodiex.features.payment.PaymentView;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;

import java.util.Scanner;

public class OrderCreateView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final OrderCreatePresenter presenter;
    private final User user;
    private final Cart cart;
    private final Restaurant restaurant;

    public OrderCreateView(User user, Cart cart, Restaurant restaurant) {
        this.user = user;
        this.cart = cart;
        this.restaurant = restaurant;
        this.presenter = new OrderCreatePresenter(this);
    }

    public void init() { presenter.placeOrder(); }

    public User getUser()             { return user; }
    public Cart getCart()             { return cart; }
    public Restaurant getRestaurant() { return restaurant; }

    public void showOrderSummary() {
        ParseHelper.printHeader("Order Summary");
        System.out.println("  Restaurant : " + restaurant.getName());
        System.out.println("  Items      : " + cart.getTotalItems());
        System.out.println("  Subtotal   : " + ParseHelper.formatPrice(cart.getSubTotal()));
        System.out.println("  Tax (5%)   : " + ParseHelper.formatPrice(cart.getTax()));
        System.out.println("  Delivery   : " + ParseHelper.formatPrice(cart.getDeliveryCharge()));
        ParseHelper.printDivider();
        System.out.println("  TOTAL      : " + ParseHelper.formatPrice(cart.getTotalAmount()));
    }

    public boolean confirmOrder() {
        System.out.println();
        System.out.print("  Confirm order? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    public void onOrderPlaced(Order order) {
        System.out.println("  Order #" + order.getId() + " placed successfully!");
        System.out.println("  Estimated delivery: ~" + restaurant.getDeliveryTime() + " mins");
        new PaymentView(user, order).init();
    }

    public void onCancelled() {
        System.out.println("  Order cancelled.");
    }
}
