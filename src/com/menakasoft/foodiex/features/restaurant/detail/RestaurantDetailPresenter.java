package com.menakasoft.foodiex.features.restaurant.detail;

import com.menakasoft.foodiex.data.dto.FoodItem;

import java.util.List;

public class RestaurantDetailPresenter {
    private final RestaurantDetailView view;
    private final RestaurantDetailModel model;

    public RestaurantDetailPresenter(RestaurantDetailView view) {
        this.view = view;
        this.model = new RestaurantDetailModel();
    }

    public void loadMenu() {
        List<FoodItem> menu = model.getMenu(view.getRestaurant().getId());
        view.showMenu(menu);
    }
}
