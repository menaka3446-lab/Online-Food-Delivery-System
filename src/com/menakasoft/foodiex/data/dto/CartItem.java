package com.menakasoft.foodiex.data.dto;

public class CartItem {
    private Long id;
    private Long foodItemId;
    private String foodName;
    private double price;
    private int quantity;

    CartItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodId() {
        return foodItemId;
    }

    public void setFoodId(Long foodId) {
        this.foodItemId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
