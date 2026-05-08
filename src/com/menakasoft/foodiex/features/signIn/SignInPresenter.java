package com.menakasoft.foodiex.features.signIn;

import com.menakasoft.foodiex.data.dto.User;
import com.menakasoft.foodiex.features.signUp.SignUpModel;
import com.menakasoft.foodiex.features.signUp.SignUpView;
import com.menakasoft.foodiex.util.ParseHelper;

public class SignInPresenter {
    private final SignInView view;
    private final SignInModel model;

    public SignInPresenter(SignInView view) {
        this.view = view;
        this.model = new SignInModel();
    }

    public void startSignIn() {
        ParseHelper.printHeader("Sign In");
        String email    = view.getEmail();
        String password = view.getPassword();
        User user = model.authenticate(email, password);
        if (user == null) {
            view.showError("Invalid email or password. Please try again.");
            return;
        }
        view.onSignInSuccess(user);
    }
}
