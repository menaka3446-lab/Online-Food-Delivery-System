package com.menakasoft.foodiex.features.home;

import com.menakasoft.foodiex.data.dto.Order;
import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.features.order.history.OrderHistoryView;
import com.menakasoft.foodiex.features.restaurant.List.RestaurantListView;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;

import java.util.List;
import java.util.Scanner;

public class HomeView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final HomePresenter presenter;
    private final User user;

    public HomeView(User user) {
        this.user = user;
        this.presenter = new HomePresenter(this);
    }

    public void init() {
        switch (user.getRole()) {
            case CUSTOMER: showCustomerMenu(); break;
            case ADMIN:    showAdminMenu();    break;
            default:
                System.out.println("  Role not supported.");
        }
    }

    // ─── CUSTOMER ───
    private void showCustomerMenu() {
        while (true) {
            ParseHelper.printHeader("FoodieX - Customer Home");
            System.out.println("  1. Browse Restaurants");
            System.out.println("  2. My Orders");
            System.out.println("  3. Sign Out");
            System.out.print("  Choose: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": new RestaurantListView(user).init(); break;
                case "2": new OrderHistoryView(user).init();   break;
                case "3":
                    System.out.println("  Signed out. Goodbye, " + user.getName() + "!");
                    return;
                default: System.out.println("  Invalid option.");
            }
        }
    }

    // ─── ADMIN ───
    private void showAdminMenu() {
        while (true) {
            ParseHelper.printHeader("FoodieX - Admin Home");
            System.out.println("  1. View All Orders");
            System.out.println("  2. Update Order Status");
            System.out.println("  3. Sign Out");
            System.out.print("  Choose: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": presenter.loadAdminOrders(); break;
                case "2": promptUpdateOrderStatus();   break;
                case "3":
                    System.out.println("  Signed out.");
                    return;
                default: System.out.println("  Invalid option.");
            }
        }
    }

    private void promptUpdateOrderStatus() {
        System.out.print("  Enter Order ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.println("  1. PLACED  2. PREPARING  3. OUT_FOR_DELIVERING  4. DELIVERED  5. CANCELLED");
            System.out.print("  Choose new status: ");
            String s = scanner.nextLine().trim();
            Order.OrderStatus status;
            switch (s) {
                case "1": status = Order.OrderStatus.PLACED;              break;
                case "2": status = Order.OrderStatus.PREPARING;           break;
                case "3": status = Order.OrderStatus.OUT_FOR_DELIVERING;  break;
                case "4": status = Order.OrderStatus.DELIVERED;           break;
                case "5": status = Order.OrderStatus.CANCELLED;           break;
                default: System.out.println("  Invalid status."); return;
            }
            presenter.updateStatus(id, status);
        } catch (NumberFormatException e) {
            System.out.println("  Invalid Order ID.");
        }
    }

    public void showAdminOrders(List<Order> orders) {
        ParseHelper.printHeader("All Orders");
        if (orders.isEmpty()) { System.out.println("  No orders yet."); return; }
        for (Order o : orders) {
            System.out.printf("  #%-3d | %-25s | %-20s | %s | %s%n",
                    o.getId(), o.getRestaurantName(),
                    ParseHelper.formatPrice(o.getTotalPrice()),
                    o.getStatus(), o.getPaymentStatus());
        }
    }

    public void showMessage(String msg) { System.out.println("  " + msg); }
}
