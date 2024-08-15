package com.example.starttdd.chapter3;

import java.time.LocalDate;

public class PayData {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

    public PayData() {
    }

    public PayData(final LocalDate firstBillingDate, final LocalDate billingDate, final int payAmount) {
        this.firstBillingDate = firstBillingDate;
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
        private LocalDate firstBillingDate;
        private LocalDate billingDate;
        private int payAmount;

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            this.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            this.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            this.payAmount = payAmount;
            return this;
        }

        public PayData build() {
            return new PayData(firstBillingDate, billingDate, payAmount);
        }
    }
}
