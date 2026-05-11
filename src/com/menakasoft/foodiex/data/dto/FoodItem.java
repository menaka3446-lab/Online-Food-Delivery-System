package com.menakasoft.foodiex.data.dto;


public class FoodItem {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private static boolean isAvailable;
    private Long preparationTime;
    private Long restaurantId;



    public FoodItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Long getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Long preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Long restaurantId){
        this.restaurantId=restaurantId;
    }
}
