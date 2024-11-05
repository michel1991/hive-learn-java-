package org.manmvou.mandaloreexpress.money.domain;
import java.math.BigDecimal;
import java.util.Random;

public class AmountFactory {
    private static final BigDecimal TEN = BigDecimal.TEN;
    private static final Random RANDOM = new Random();

    public static Amount amount() {
        return new Amount(TEN);
    }

    public static Amount amount(int value) {
        return new Amount(BigDecimal.valueOf(value));
    }

    public static Amount amount(double value) {
        return new Amount(BigDecimal.valueOf(value));
    }

    public static Amount amount(String value) {
        return new Amount(new BigDecimal(value));
    }

    public static Amount randomAmount() {
        double randomValue = 500.0 + (500.0 * RANDOM.nextDouble()); // Generates a value between 500.0 and 1000.0
        return new Amount(BigDecimal.valueOf(randomValue));
    }
}
