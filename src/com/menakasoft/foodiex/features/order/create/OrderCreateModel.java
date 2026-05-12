package com.menakasoft.foodiex.features.order.create;

import com.menakasoft.foodiex.data.dto.*;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;

import java.util.ArrayList;
import java.util.List;

public class OrderCreateModel {
    Order createOrder(User user, Cart cart, Restaurant restaurant) {
        Order order = new Order();
        order.setUserId(user.getId());
        order.setRestaurantId(restaurant.getId());
        order.setRestaurantName(restaurant.getName());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setFoodId(ci.getFoodId());
            oi.setFoodName(ci.getFoodName());
            oi.setPrice(ci.getPrice());
            oi.setQuantity(ci.getQuantity());
            orderItems.add(oi);
        }
        order.setItems(orderItems);
        order.setTotalItems(cart.getTotalItems());
        order.setSubTotal(cart.getSubTotal());
        order.setTax(cart.getTax());
        order.setDiscount(cart.getDiscount());
        order.setDeliveryCharge(cart.getDeliveryCharge());
        order.setTotalPrice(cart.getTotalAmount());
        order.setStatus(Order.OrderStatus.PLACED);
        order.setPaymentStatus(Order.PaymentStatus.PENDING);

        Order saved = FoodieXDB.getInstance().addOrder(order);
        FoodieXDB.getInstance().clearCart(user.getId());
        return saved;
    }
}
