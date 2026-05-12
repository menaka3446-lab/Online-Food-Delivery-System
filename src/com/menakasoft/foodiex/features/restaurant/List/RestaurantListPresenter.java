package com.menakasoft.foodiex.features.restaurant.List;

import com.menakasoft.foodiex.data.dto.Restaurant;

import java.util.List;

public class RestaurantListPresenter {

    private final RestaurantListView view;
    private final RestaurantListModel model;

    public RestaurantListPresenter(RestaurantListView view) {
        this.view = view;
        this.model = new RestaurantListModel();
    }

    public void loadRestaurants() {
        List<Restaurant> list = model.getRestaurants();
        view.showRestaurants(list);
    }
}
