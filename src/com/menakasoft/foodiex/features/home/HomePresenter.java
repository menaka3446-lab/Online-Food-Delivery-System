package com.menakasoft.foodiex.features.home;

import com.menakasoft.foodiex.data.dto.Order;

import java.util.List;

public class HomePresenter {
    private final HomeView view;
    private final HomeModel model;

    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new HomeModel();
    }

    public void loadAdminOrders() {
        List<Order> orders = model.getAllOrders();
        view.showAdminOrders(orders);
    }

    public void updateStatus(Long orderId, Order.OrderStatus status) {
        boolean ok = model.updateOrderStatus(orderId, status);
        view.showMessage(ok ? "Order status updated." : "Order not found.");
    }
}
