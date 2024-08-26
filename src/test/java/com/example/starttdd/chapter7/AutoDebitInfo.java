package com.example.starttdd.chapter7;

import java.time.LocalDateTime;

public class AutoDebitInfo {
    private String userId;
    private String cardNumber;
    private LocalDateTime registTime;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime registTime) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.registTime = registTime;
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
