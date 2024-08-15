package com.example.starttdd.chapter3;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(final PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            final LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if (payData.getFirstBillingDate().getDayOfMonth() != payData.getBillingDate().getDayOfMonth()) {
                final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
                if (dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
