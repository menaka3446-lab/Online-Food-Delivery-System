package com.menakasoft.foodiex.features.food;

import com.menakasoft.foodiex.data.dto.FoodItem;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;

import java.util.List;

public class FoodListModel {
    List<FoodItem> getFoodItemsByRestaurant(Long restaurantId) {
        return FoodieXDB.getInstance().getFoodItemsByRestaurant(restaurantId);
    }
}
