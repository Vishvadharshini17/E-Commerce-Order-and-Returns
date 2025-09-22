package com.kce.ecommerce.model;

public class Payment {
    private String paymentId;
    private double amount;
    private boolean successful;

    public Payment(String paymentId, double amount, boolean successful) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }
}
