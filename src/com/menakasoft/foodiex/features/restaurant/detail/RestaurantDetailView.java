package com.menakasoft.foodiex.features.restaurant.detail;

import com.menakasoft.foodiex.data.dto.FoodItem;
import com.menakasoft.foodiex.data.dto.Restaurant;
import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.features.cart.CartView;
import com.menakasoft.foodiex.features.food.FoodListView;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;
import java.util.List;
import java.util.Scanner;


public class RestaurantDetailView {

    private final Scanner scanner = ConsoleInput.getScanner();
    private final RestaurantDetailPresenter presenter;
    private final User user;
    private final Restaurant restaurant;

    public RestaurantDetailView(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
        this.presenter = new RestaurantDetailPresenter(this);
    }

    public void init() {
        presenter.loadMenu();
    }

    public Restaurant getRestaurant() { return restaurant; }

    public void showMenu(List<FoodItem> menu) {
        ParseHelper.printHeader(restaurant.getName() + " - Menu");
        System.out.println("  Location : " + restaurant.getLocation());
        System.out.println("  Rating   : " + restaurant.getRating());
        System.out.println("  Delivery : ~" + restaurant.getDeliveryTime() + " mins");
        ParseHelper.printDivider();
        if (menu.isEmpty()) {
            System.out.println("  No items available.");
            return;
        }
        for (int i = 0; i < menu.size(); i++) {
            FoodItem f = menu.get(i);
            System.out.printf("  %d. %-22s | %-10s | %s | ~%d mins%n",
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
            new FoodListView(user, restaurant).init();
        }
    }
}
