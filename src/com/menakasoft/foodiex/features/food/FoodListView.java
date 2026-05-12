package com.menakasoft.foodiex.features.food;

import com.menakasoft.foodiex.data.dto.FoodItem;
import com.menakasoft.foodiex.data.dto.Restaurant;
import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.features.cart.CartView;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;

import java.util.List;
import java.util.Scanner;

public class FoodListView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final FoodListPresenter presenter;
    private final User user;
    private final Restaurant restaurant;

    public FoodListView(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
        this.presenter = new FoodListPresenter(this);
    }

    public void init() {
        presenter.loadFoodItems(restaurant.getId());
    }

    public Restaurant getRestaurant() { return restaurant; }
    public User getUser()             { return user; }

    public void showFoodItems(List<FoodItem> items) {
        ParseHelper.printHeader(restaurant.getName() + " - Food Items");
        if (items.isEmpty()) {
            System.out.println("  No food items available for this restaurant.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            FoodItem f = items.get(i);
            System.out.printf("  %d. %-22s | %-12s | %s | ~%d mins%n",
                    i + 1, f.getName(), f.getCategory(),
                    ParseHelper.formatPrice(f.getPrice()), f.getPreparationTime());
            if (f.getDescription() != null && !f.getDescription().isEmpty())
                System.out.println("       " + f.getDescription());
        }
        System.out.println();
        System.out.println("  1. Add items to cart");
        System.out.println("  0. Back");
        System.out.print("  Choose: ");
        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            new CartView(user, restaurant, items).init();
        }
    }

    public void showMessage(String msg) {
        System.out.println("  " + msg);
    }
}
