package com.menakasoft.foodiex.features.payment;

public class PaymentPresenter {
    private final PaymentView view;
    private final PaymentModel model;

    public PaymentPresenter(PaymentView view) {
        this.view = view;
        this.model = new PaymentModel();
    }

    public void startPayment() {
        String method = view.selectPaymentMethod();
        if (method == null) { view.showMessage("Invalid payment method."); return; }
        boolean success = model.processPayment(view.getOrder().getId(), method);
        if (success) view.onPaymentSuccess(method);
        else view.onPaymentFailed();
    }
}
