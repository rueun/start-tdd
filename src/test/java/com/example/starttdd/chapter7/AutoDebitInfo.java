package com.example.starttdd.chapter7;

public class AutoDebitInfo {
    private String userId;
    private String cardNumber;

    public AutoDebitInfo(String userId, String cardNumber) {
        this.userId = userId;
        this.cardNumber = cardNumber;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
