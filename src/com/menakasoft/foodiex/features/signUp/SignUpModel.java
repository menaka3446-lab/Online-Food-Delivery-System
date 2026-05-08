package com.menakasoft.foodiex.features.signUp;

import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;
import com.menakasoft.foodiex.util.ParseHelper;

public class SignUpModel {
    String validateName(String name) {
        if (name == null || name.isEmpty()) return "Name cannot be empty";
        if (name.length() < 2 || name.length() > 50) return "Name must be 2-50 characters";
        return null;
    }

    String validateEmail(String email) {
        if (!ParseHelper.isValidEmail(email)) return "Enter a valid email address";
        if (FoodieXDB.getInstance().getUserByEmail(email) != null) return "Email already registered";
        return null;
    }

    String validatePassword(String password) {
        if (!ParseHelper.isValidPassword(password))
            return "Password must be at least 8 characters with letters and numbers";
        return null;
    }

    String validatePhone(String phone) {
        if (!ParseHelper.isValidMobile(phone)) return "Enter a valid 10-digit mobile number";
        return null;
    }

    String validateAddress(String address) {
        if (address == null || address.isEmpty()) return "Address cannot be empty";
        return null;
    }

    User registerUser(String name, String email, String password, String phone, String address) {
        User user = new User();
        user.setName(name);
        user.setEmail(email.toLowerCase());
        user.setPassword(password);
        user.setPhoneNumber(phone);
        user.setAddress(address);
        user.setRole(User.UserRole.CUSTOMER);
        user.setStatus(User.UserStatus.ACTIVE);
        return FoodieXDB.getInstance().addUser(user);
    }

}
