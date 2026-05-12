package com.menakasoft.foodiex.features.restaurant.List;

import com.menakasoft.foodiex.data.dto.Restaurant;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;

import java.util.List;

public class RestaurantListModel {

    List<Restaurant> getRestaurants() {
        return FoodieXDB.getInstance().getAllRestaurants();
    }
}
