package com.menakasoft.foodiex;

import com.menakasoft.foodiex.features.signIn.SignInView;
import com.menakasoft.foodiex.features.signUp.SignUpView;
import com.menakasoft.foodiex.util.ConsoleInput;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class FoodieXApplication {

    public static final int VERSION_NO = 1;
    public static final String VERSION_NAME = "1.0.0";

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("        Welcome to FoodieX");
        System.out.println("        Version " + VERSION_NAME);
        System.out.println("==========================================");
        showLandingMenu();
    }

    private static void showLandingMenu() {
        Scanner scanner = ConsoleInput.getScanner();
        while (true) {
            System.out.println();
            System.out.println("  1. Sign Up");
            System.out.println("  2. Sign In");
            System.out.println("  3. Exit");
            System.out.print("  Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    new SignUpView().init();
                    break;
                case "2":
                    new SignInView().init();
                    break;
                case "3":
                    System.out.println("  Thank you for using FoodieX. Goodbye!");
                    return;
                default:
                    System.out.println("  Invalid option. Please try again.");
            }
        }
    }
}