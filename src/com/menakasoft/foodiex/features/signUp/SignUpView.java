package com.menakasoft.foodiex.features.signUp;

import com.menakasoft.foodiex.features.home.HomeView;
import com.menakasoft.foodiex.features.signIn.SignInPresenter;
import com.menakasoft.foodiex.util.ConsoleInput;

import java.util.Scanner;


public class SignUpView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final SignUpPresenter presenter;

    public SignUpView() {
        this.presenter = new SignUpPresenter(this);
    }

    public void init() {
        presenter.startSignUp();
    }

    public String getName() {
        System.out.print("Enter Name: ");
        return scanner.nextLine().trim();
    }

    public String getEmail() {
        System.out.print("Enter Email: ");
        return scanner.nextLine().trim();
    }

    public String getPassword() {
        System.out.print("Enter Password (min 8 chars, letters+numbers): ");
        return scanner.nextLine().trim();
    }

    public String getPhoneNumber() {
        System.out.print("Enter Phone Number: ");
        return scanner.nextLine().trim();
    }

    public String getAddress() {
        System.out.print("Enter Delivery Address: ");
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        System.out.println("  Error: " + message);
    }

    public void showSuccess(String message) {
        System.out.println("  " + message);
    }
}