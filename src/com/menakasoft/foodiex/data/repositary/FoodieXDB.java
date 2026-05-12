package com.menakasoft.foodiex.data.repositary;

import com.menakasoft.foodiex.data.dto.*;

import java.util.ArrayList;
import java.util.List;

public class FoodieXDB {
    private FoodieXDB() {
        seedData();

    }

    private static volatile FoodieXDB foodieXDB = null;

    public static final FoodieXDB getInstance() {
        if (foodieXDB == null) {
            foodieXDB = new FoodieXDB();
        }
        return foodieXDB;
    }


    private final List<User> users = new ArrayList<>();
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final List<FoodItem> foodItems = new ArrayList<>();
    private final List<Cart> carts = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();


    private long cartPk = 0L;
    private long userPk = 0L;
    private long foodItemPk = 0L;
    private long orderPk = 0L;
    private long restaurantPk = 0L;

    // Users Database
    public User addUser(User user) {
        if (user == null || user.getEmail() == null) return null;
        userPk++;
        user.setId(userPk);
        user.setCreatedAt(System.currentTimeMillis());
        if (user.getStatus() == null) user.setStatus(User.UserStatus.ACTIVE);
        users.add(user);
        return user;
    }

    public User getUserByEmail(String email) {
        if (email == null) return null;
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email.trim())) {
                return user;
            }
        }
        return null;
    }

    public User authenticateUser(String email, String password) {
        User user = getUserByEmail(email);
        if (user == null || password == null || !password.equals(user.getPassword())) return null;
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
// Restaurant DataBase

    public Restaurant addRestaurant(Restaurant restaurant) {
        if (restaurant == null) return null;
        restaurantPk++;
        restaurant.setId((int) restaurantPk);
        restaurants.add(restaurant);
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(restaurants);
    }

    public Restaurant getRestaurantById(Long id) {
        if (id == null) return null;
        for (Restaurant restaurant : restaurants) {
            if (id.equals(restaurant.getId())) return restaurant;
        }
        return null;
    }


    // FoodItem DataBase
    public FoodItem addFoodItem(FoodItem foodItem) {
        if (foodItem == null) return null;
        foodItemPk++;
        foodItem.setId(foodItemPk);
        foodItems.add(foodItem);
        return foodItem;
    }

    public List<FoodItem> getFoodItemsByRestaurant(Long restaurantId) {

        List<FoodItem> result = new ArrayList<>();
        if (restaurantId == null) return result;
        for (FoodItem foodItem : foodItems) {
            if (restaurantId.equals(foodItem.getRestaurantId()) && foodItem.isAvailable()) {
                result.add(foodItem);
            }
        }
        return result;
    }

    public FoodItem getFoodItemById(Long id) {
        if (id == null) return null;
        for (FoodItem foodItem : foodItems) {
            if (id.equals(foodItem.getId())) return foodItem;
        }
        return null;
    }

    //cart DataBase

    public Cart getCartByUserId(Long userId){
        if (userId == null) return null;
        for (Cart c : carts) if (userId.equals(c.getUserId())) return c;
        return null;
    }
    public Cart saveCart(Cart cart) {
        // Remove old cart for user, then add new one
        carts.removeIf(c -> cart.getUserId().equals(c.getUserId()));
        carts.add(cart);
        return cart;
    }

    public void clearCart(Long userId) {
        carts.removeIf(c -> userId.equals(c.getUserId()));
    }

    // OrderDataBase

    public Order addOrder(Order order) {
        if (order == null) return null;
        orderPk++;
        order.setId(orderPk);
        order.setCreatedAt(System.currentTimeMillis());
        if (order.getStatus() == null) order.setStatus(Order.OrderStatus.PLACED);
        orders.add(order);
        return order;
    }
    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> result = new ArrayList<>();
        if (userId == null) return result;
        for (Order order : orders) if (userId.equals(order.getUserId())) result.add(order);
        return result;
    }
    public Order getOrderById(Long id) {
        if (id == null) return null;
        for (Order order : orders) if (id.equals(order.getId())) return order;
        return null;
    }
    public boolean updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = getOrderById(orderId);
        if (order == null) return false;
        order.setStatus(status);
        return true;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    //SeedData
    private void seedData() {
        // Admin user
        User admin = new User();
        admin.setName("Admin");
        admin.setEmail("admin@foodiex.com");
        admin.setPassword("Admin@123");
        admin.setPhoneNumber("9000000000");
        admin.setRole(User.UserRole.ADMIN);
        addUser(admin);

        Restaurant r1 = new Restaurant();
        r1.setName("Murugan Idli Shop");
        r1.setLocation("Anna Nagar, Chennai");
        r1.setPhoneNumber("9111111111");
        r1.setRating(4.5);
        r1.setOpen(true);
        r1.setDeliveryTime(30);
        addRestaurant(r1);

        Restaurant r2 = new Restaurant();
        r2.setName("Dindigul Thalappakatti");
        r2.setLocation("T. Nagar, Chennai");
        r2.setPhoneNumber("9222222222");
        r2.setRating(4.8);
        r2.setOpen(true);
        r2.setDeliveryTime(45);
        addRestaurant(r2);



        String[][] menuR1 = {
                {"Idli (2 pcs)", "Soft idli with sambar & chutney", "30", "Breakfast", "10"},
                {"Dosa", "Crispy plain dosa", "40", "Breakfast", "8"},
                {"Pongal", "Ven pongal with sambar", "50", "Breakfast", "12"},
                {"Filter Coffee", "Fresh filter coffee", "20", "Drinks", "5"},
        };

        for (String[] m : menuR1) {
            FoodItem f = new FoodItem();
            f.setName(m[0]);
            f.setDescription(m[1]);
            f.setPrice(Double.parseDouble(m[2]));
            f.setCategory(m[3]);
            f.setPreparationTime(Long.parseLong(m[4]));
            f.setAvailable(true);
            f.setRestaurantId((long) r1.getId());
            addFoodItem(f);
        }

        // Food items for R2
        String[][] menuR2 = {
                {"Biryani (Full)", "Aromatic chicken biryani", "180", "Main", "25"},
                {"Biryani (Half)", "Half plate chicken biryani", "100", "Main", "20"},
                {"Parotta", "Layered parotta with kurma", "60", "Main", "15"},
                {"Raita", "Onion raita", "20", "Sides", "5"},
                {"Lassi", "Sweet or salted lassi", "40", "Drinks", "5"},
        };
        for (String[] m : menuR2) {
            FoodItem f = new FoodItem();
            f.setName(m[0]);
            f.setDescription(m[1]);
            f.setPrice(Double.parseDouble(m[2]));
            f.setCategory(m[3]);
            f.setPreparationTime(Long.parseLong(m[4]));
            f.setAvailable(true);
            f.setRestaurantId((long) r2.getId());
            addFoodItem(f);
        }
    }




    }