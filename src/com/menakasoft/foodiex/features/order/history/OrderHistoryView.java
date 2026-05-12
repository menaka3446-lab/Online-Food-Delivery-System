package com.menakasoft.foodiex.features.order.history;

import com.menakasoft.foodiex.data.dto.*;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderHistoryView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final OrderHistoryPresenter presenter;
    private final User user;

    public OrderHistoryView(User user) {
        this.user = user;
        this.presenter = new OrderHistoryPresenter(this);
    }

    public void init() { presenter.loadHistory(); }
    public User getUser() { return user; }

    public void showOrders(List<Order> orders) {
        ParseHelper.printHeader("My Orders");
        if (orders.isEmpty()) {
            System.out.println("  No orders yet.");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Order o : orders) {
            System.out.println();
            System.out.println("  Order #" + o.getId() + " | " + o.getRestaurantName());
            System.out.println("  Status : " + o.getStatus() + " | Payment: " + o.getPaymentStatus());
            System.out.println("  Total  : " + ParseHelper.formatPrice(o.getTotalPrice()));
            if (o.getCreatedAt() != null)
                System.out.println("  Date   : " + sdf.format(new Date(o.getCreatedAt())));
            System.out.println("  Items  :");
            for (OrderItem oi : o.getItems())
                System.out.printf("    - %-20s x%d  %s%n",
                        oi.getFoodName(), oi.getQuantity(), ParseHelper.formatPrice(oi.getTotal()));
            ParseHelper.printDivider();
        }
    }
}
