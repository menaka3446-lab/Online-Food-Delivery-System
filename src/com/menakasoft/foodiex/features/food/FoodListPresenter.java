package com.menakasoft.foodiex.features.food;

import com.menakasoft.foodiex.data.dto.FoodItem;

import java.util.List;

public class FoodListPresenter {

    private final FoodListView view;
    private final FoodListModel model;

    public FoodListPresenter(FoodListView view) {
        this.view = view;
        this.model = new FoodListModel();
    }

    public void loadFoodItems(Long restaurantId) {
        List<FoodItem> items = model.getFoodItemsByRestaurant(restaurantId);
        view.showFoodItems(items);
    }
}
