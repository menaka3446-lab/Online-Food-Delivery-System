package com.menakasoft.foodiex.features.restaurant.List;

import com.menakasoft.foodiex.data.dto.Restaurant;
import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.features.restaurant.detail.RestaurantDetailView;
import com.menakasoft.foodiex.util.ConsoleInput;
import com.menakasoft.foodiex.util.ParseHelper;
import java.util.List;
import java.util.Scanner;

public class RestaurantListView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final RestaurantListPresenter presenter;
    private final User user;

    public RestaurantListView(User user) {
        this.user = user;
        this.presenter = new RestaurantListPresenter(this);
    }

    public void init() {
        presenter.loadRestaurants();
    }

    public void showRestaurants(List<Restaurant> list) {
        ParseHelper.printHeader("Restaurants");
        if (list.isEmpty()) {
            System.out.println("  No restaurants available.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Restaurant r = list.get(i);
            System.out.printf("  %d. %-25s | %s | Rating: %.1f | ~%d mins | %s%n",
                    i + 1, r.getName(), r.getLocation(), r.getRating(),
                    r.getDeliveryTime(), r.isOpen() ? "OPEN" : "CLOSED");
        }
        System.out.println();
        System.out.print("  Select restaurant number (0 to go back): ");
        String choice = scanner.nextLine().trim();
        try {
            int idx = Integer.parseInt(choice);
            if (idx == 0) return;
            if (idx >= 1 && idx <= list.size()) {
                new RestaurantDetailView(user, list.get(idx - 1)).init();
            } else {
                System.out.println("  Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  Invalid input.");
        }
    }
}
