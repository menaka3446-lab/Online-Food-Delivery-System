package com.menakasoft.foodiex.features.payment;
import com.menakasoft.foodiex.data.dto.Order;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;

public class PaymentModel {
    boolean processPayment(Long orderId, String method) {
        // Simulate payment success
        Order order = FoodieXDB.getInstance().getOrderById(orderId);
        if (order == null) return false;
        order.setPaymentStatus(Order.PaymentStatus.SUCCESS);
        order.setStatus(Order.OrderStatus.PREPARING);
        return true;
    }
}
