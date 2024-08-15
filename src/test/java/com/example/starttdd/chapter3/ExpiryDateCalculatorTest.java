package com.example.starttdd.chapter3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpiryDateCalculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build()
                , LocalDate.of(2019, 4, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 4, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 5, 1));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 30));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를_때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31)) // 첫 납부일은 31일
                .billingDate(LocalDate.of(2019, 2, 28)) // 만료일 일자는 28일
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));

    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(20_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019, 5, 1));


        PayData payData2 = PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(30_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019, 6, 1));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를_때_이만원_이상_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(20_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019, 4, 30));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(40_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019, 6, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 3, 31))
                .billingDate(LocalDate.of(2019, 4, 30))
                .payAmount(30_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 28))
                .payAmount(100_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2020, 1, 28));

        PayData payData2 = PayData.builder()
                .billingDate(LocalDate.of(2024, 2, 29))
                .payAmount(100_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2025, 2, 28));
    }

    @Test
    void 십만원을_납부하면_1년_제공_첫_납부일과_만료일_일자가_다를_때() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(100_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2020, 2, 29));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2020, 1, 31))
                .billingDate(LocalDate.of(2020, 2, 29))
                .payAmount(100_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2021, 2, 28));
    }

    @Test
    void 십만원_이상_납부하는_경우_계산() {
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .payAmount(110_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2020, 2, 29));

        PayData payData2 = PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .payAmount(210_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2021, 2, 28));

        PayData payData3 = PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .payAmount(230_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2021, 4, 30));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를_때_십만원_이상_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(110_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2020, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(210_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2021, 3, 31));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(230_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2021, 5, 31));

        PayData payData4 = PayData.builder()
                .firstBillingDate(LocalDate.of(2018, 1, 30))
                .billingDate(LocalDate.of(2018, 2, 28))
                .payAmount(200_000)
                .build();
        assertExpiryDate(payData4, LocalDate.of(2020, 2, 29));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, expiryDate);
    }
}
