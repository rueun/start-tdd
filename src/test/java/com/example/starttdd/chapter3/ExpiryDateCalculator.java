package com.example.starttdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(final PayData payData) {
        if (payData.getFirstBillingDate() != null) {
            final LocalDate candidateExp = payData.getBillingDate().plusMonths(1);
            if (payData.getFirstBillingDate().getDayOfMonth() != payData.getBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
