package com.menakasoft.foodiex.features.signUp;

import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.util.ParseHelper;

public class SignUpPresenter {
    private final SignUpView view;
    private final SignUpModel model;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
        this.model = new SignUpModel();
    }

    public void startSignUp() {
        ParseHelper.printHeader("Create Account");

        String name;
        while (true) {
            name = view.getName();
            String err = model.validateName(name);
            if (err == null) break;
            view.showError(err);
        }

        String email;
        while (true) {
            email = view.getEmail();
            String err = model.validateEmail(email);
            if (err == null) break;
            view.showError(err);
        }

        String password;
        while (true) {
            password = view.getPassword();
            String err = model.validatePassword(password);
            if (err == null) break;
            view.showError(err);
        }

        String phone;
        while (true) {
            phone = view.getPhoneNumber();
            String err = model.validatePhone(phone);
            if (err == null) break;
            view.showError(err);
        }

        String address = view.getAddress();

        model.registerUser(name, email, password, phone, address);
        view.showSuccess("Account created successfully! Please sign in.");
    }
}
