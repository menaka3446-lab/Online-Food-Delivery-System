package com.menakasoft.foodiex.features.signIn;

import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.data.repositary.FoodieXDB;
import com.menakasoft.foodiex.util.ParseHelper;

public class SignInModel {
    User authenticate(String email, String password) {
        if (email == null || password == null) return null;
        return FoodieXDB.getInstance().authenticateUser(email.trim(), password);
    }
}
