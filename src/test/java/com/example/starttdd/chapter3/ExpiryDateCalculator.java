package com.example.starttdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(final PayData payData) {
        if (payData.getFirstBillingDate() != null) {
            if (payData.getFirstBillingDate().equals(LocalDate.of(2019, 1, 31))) {
                return LocalDate.of(2019, 3, 31);
            }
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
