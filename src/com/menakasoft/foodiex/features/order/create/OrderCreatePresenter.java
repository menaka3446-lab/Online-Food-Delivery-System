package com.menakasoft.foodiex.features.order.create;

import com.menakasoft.foodiex.data.dto.Order;

public class OrderCreatePresenter {
    private final OrderCreateView view;
    private final OrderCreateModel model;

    public OrderCreatePresenter(OrderCreateView view) {
        this.view = view;
        this.model = new OrderCreateModel();
    }

    public void placeOrder() {
        view.showOrderSummary();
        if (!view.confirmOrder()) { view.onCancelled(); return; }
        Order order = model.createOrder(view.getUser(), view.getCart(), view.getRestaurant());
        view.onOrderPlaced(order);
    }
}
