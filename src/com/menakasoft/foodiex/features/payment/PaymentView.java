package com.menakasoft.foodiex.features.payment;

import com.menakasoft.foodiex.data.dto.Order;
import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;

import java.util.Scanner;

public class PaymentView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final PaymentPresenter presenter;
    private final User user;
    private final Order order;

    public PaymentView(User user, Order order) {
        this.user = user;
        this.order = order;
        this.presenter = new PaymentPresenter(this);
    }

    public void init() { presenter.startPayment(); }

    public Order getOrder() { return order; }

    public String selectPaymentMethod() {
        ParseHelper.printHeader("Payment");
        System.out.println("  Amount to pay: " + ParseHelper.formatPrice(order.getTotalPrice()));
        System.out.println();
        System.out.println("  1. UPI");
        System.out.println("  2. Credit / Debit Card");
        System.out.println("  3. Cash on Delivery");
        System.out.print("  Choose payment method: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": return "UPI";
            case "2": return "Card";
            case "3": return "COD";
            default:  return null;
        }
    }

    public void onPaymentSuccess(String method) {
        System.out.println();
        System.out.println("  Payment via " + method + " successful!");
        System.out.println("  Your order is now being prepared.");
        System.out.println("  Order ID: #" + order.getId());
    }

    public void onPaymentFailed() {
        System.out.println("  Payment failed. Please try again.");
    }

    public void showMessage(String msg) { System.out.println("  " + msg); }
}
