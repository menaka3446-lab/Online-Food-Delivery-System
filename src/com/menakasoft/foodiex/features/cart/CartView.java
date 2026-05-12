package com.menakasoft.foodiex.features.cart;

import com.menakasoft.foodiex.data.dto.*;
import com.menakasoft.foodiex.features.order.create.OrderCreateView;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final CartPresenter presenter;
    private final User user;
    private final Restaurant restaurant;
    private final List<FoodItem> menu;

    public CartView(User user, Restaurant restaurant, List<FoodItem> menu) {
        this.user = user;
        this.restaurant = restaurant;
        this.menu = menu;
        this.presenter = new CartPresenter(this);
    }

    public void init() {
        presenter.startCart();
    }

    public User getUser()             { return user; }
    public Restaurant getRestaurant() { return restaurant; }
    public List<FoodItem> getMenu()   { return menu; }

    public void showCartMenu(Cart cart) {
        List<CartItem> item = cart.getItems();
        if (item == null) {                          // ← இந்த null check add பண்ணு
            item = new ArrayList<>();
            cart.setItems(item);
        }
        while (true) {
            ParseHelper.printHeader("Your Cart - " + restaurant.getName());
            List<CartItem> items = cart.getItems();
            if (items.isEmpty()) {
                System.out.println("  Cart is empty.");
            } else {
                for (int i = 0; i < items.size(); i++) {
                    CartItem ci = items.get(i);
                    System.out.printf("  %d. %-22s x%d  = %s%n",
                            i + 1, ci.getFoodName(), ci.getQuantity(),
                            ParseHelper.formatPrice(ci.getTotal()));
                }
                ParseHelper.printDivider();
                System.out.println("  Subtotal  : " + ParseHelper.formatPrice(cart.getSubTotal()));
                System.out.println("  Tax (5%)  : " + ParseHelper.formatPrice(cart.getTax()));
                System.out.println("  Delivery  : " + ParseHelper.formatPrice(cart.getDeliveryCharge()));
                System.out.println("  TOTAL     : " + ParseHelper.formatPrice(cart.getTotalAmount()));
            }
            System.out.println();
            System.out.println("  1. Add more items");
            System.out.println("  2. Remove an item");
            System.out.println("  3. Proceed to payment");
            System.out.println("  0. Back");
            System.out.print("  Choose: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": presenter.addItems(cart); break;
                case "2": presenter.removeItem(cart); break;
                case "3":
                    if (items.isEmpty()) { System.out.println("  Cart is empty!"); break; }
                    new OrderCreateView(user, cart, restaurant).init();
                    return;
                case "0": return;
                default: System.out.println("  Invalid option.");
            }
        }
    }

    public int pickFoodItem() {
        ParseHelper.printHeader("Add Item");
        for (int i = 0; i < menu.size(); i++)
            System.out.printf("  %d. %-22s %s%n", i + 1,
                    menu.get(i).getName(), ParseHelper.formatPrice(menu.get(i).getPrice()));
        System.out.print("  Select item number: ");
        try { return Integer.parseInt(scanner.nextLine().trim()) - 1; }
        catch (NumberFormatException e) { return -1; }
    }

    public int pickQuantity() {
        System.out.print("  Enter quantity: ");
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return 0; }
    }

    public int pickRemoveIndex(int size) {
        System.out.print("  Enter item number to remove: ");
        try { return Integer.parseInt(scanner.nextLine().trim()) - 1; }
        catch (NumberFormatException e) { return -1; }
    }

    public void showMessage(String msg) { System.out.println("  " + msg); }
}
