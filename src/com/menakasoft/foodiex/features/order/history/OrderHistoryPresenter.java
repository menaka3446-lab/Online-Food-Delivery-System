package com.menakasoft.foodiex.features.order.history;

import com.menakasoft.foodiex.data.dto.Order;

import java.util.List;

public class OrderHistoryPresenter {
    private final OrderHistoryView view;
    private final OrderHistoryModel model;

    public OrderHistoryPresenter(OrderHistoryView view) {
        this.view = view;
        this.model = new OrderHistoryModel();
    }

    public void loadHistory() {
        List<Order> orders = model.getOrders(view.getUser().getId());
        view.showOrders(orders);
    }
}
