package com.example.starttdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(final LocalDate billingDate, final int payAmount) {
        return billingDate.plusMonths(1);
    }
}
