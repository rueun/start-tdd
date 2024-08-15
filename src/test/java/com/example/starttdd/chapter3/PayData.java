package com.example.starttdd.chapter3;

import java.time.LocalDate;
import java.util.Locale;

public class PayData {
    private LocalDate billingDate;
    private int payAmount;

    public PayData(final LocalDate billingDate, final int payAmount) {
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDate billingDate;
        private int payAmount;

        public Builder billingDate(LocalDate billingDate) {
            this.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            this.payAmount = payAmount;
            return this;
        }

        public PayData build() {
            return new PayData(billingDate, payAmount);
        }
    }
}
