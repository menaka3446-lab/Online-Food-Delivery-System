package com.menakasoft.foodiex.features.order.history;

import com.menakasoft.foodiex.data.dto.Order;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;

import java.util.List;

public class OrderHistoryModel {
    List<Order> getOrders(Long userId) {
        return FoodieXDB.getInstance().getOrdersByUserId(userId);
    }
}
