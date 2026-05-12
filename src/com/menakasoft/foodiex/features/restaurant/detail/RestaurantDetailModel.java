package com.menakasoft.foodiex.features.restaurant.detail;

import com.menakasoft.foodiex.data.dto.FoodItem;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;

import java.util.List;

public class RestaurantDetailModel {
    List<FoodItem> getMenu(Long restaurantId) {
        return FoodieXDB.getInstance().getFoodItemsByRestaurant(restaurantId);
    }
}
