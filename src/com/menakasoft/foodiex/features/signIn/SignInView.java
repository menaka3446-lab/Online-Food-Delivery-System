package com.menakasoft.foodiex.features.signIn;

import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.features.home.HomeView;
import com.menakasoft.foodiex.util.ConsoleInput;

import java.util.Scanner;

public class SignInView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final SignInPresenter presenter;

    public SignInView() {
        this.presenter = new SignInPresenter(this);
    }

    public void init() {
        presenter.startSignIn();
    }

    public String getEmail() {
        System.out.print("Enter Email: ");
        return scanner.nextLine().trim();
    }

    public String getPassword() {
        System.out.print("Enter Password: ");
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        System.out.println("  Error: " + message);
    }

    public void onSignInSuccess(User user) {
        System.out.println("  Welcome, " + user.getName() + "!");
        new HomeView(user).init();
    }
}

