package com.menakasoft.foodiex.features.home;

import com.menakasoft.foodiex.data.dto.Order;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;

import java.util.List;

public class HomeModel {
    List<Order> getAllOrders() {
        return FoodieXDB.getInstance().getAllOrders();
    }
    boolean updateOrderStatus(Long orderId, Order.OrderStatus status) {
        return FoodieXDB.getInstance().updateOrderStatus(orderId, status);
    }
}
