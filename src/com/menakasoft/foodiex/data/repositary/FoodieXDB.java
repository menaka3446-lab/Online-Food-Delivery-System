package com.menakasoft.foodiex.data.repositary;

import com.menakasoft.foodiex.data.dto.*;

import java.util.ArrayList;
import java.util.List;

public class FoodieXDB {
    private FoodieXDB() {

    }

    private static FoodieXDB foodieXDB = null;

    public static final FoodieXDB getInstance() {
        if (foodieXDB == null) {
            foodieXDB = new FoodieXDB();
        }
        return foodieXDB;
    }

    private final List<Cart> carts = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<FoodItem> foodItems = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<Restaurant> restaurants = new ArrayList<>();

    private long cartPk = 0L;
    private long userPk = 0L;
    private long foodItemPk = 0L;
    private long orderPk = 0L;
    private long restaurantPk = 0L;

    public User addUser(User user){
        userPk++;
        user.setId(userPk);
        users.add(user);
        return user;
    }

    public User getUserByEmail(String email){
        for(User u : users){
            if(u.getEmail().equalsIgnoreCase(email)){
                return u;
            }
        }
        return null;
    }

    public FoodItem addFoodItem(FoodItem foodItem){
        foodItemPk++;
        foodItem.setId(foodItemPk);
        foodItems.add(foodItem);
        return foodItem;
    }
    public List<FoodItem> getAllFoodItems(){
        return new ArrayList<>(foodItems);
    }

    public Cart saveCart(Cart cart){
        carts.add(cart);
        return cart;
    }
    public Cart getCartByUserId(Long userId){

    }
}