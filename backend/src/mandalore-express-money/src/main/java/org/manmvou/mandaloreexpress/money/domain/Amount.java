package org.manmvou.mandaloreexpress.money.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount implements Comparable<Amount> {
    private final BigDecimal value;

    public Amount(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be strictly positive");
        }
        this.value = value;
    }

    public Amount plus(Amount amount) {
        return new Amount(this.value.add(amount.value));
    }

    public Amount minus(Amount amount) {
        return new Amount(this.value.subtract(amount.value));
    }

    //multiply
    public Amount times(BigDecimal scalar) {
        return new Amount(multiplyWithRounding(this.value, scalar));
    }

    @Override
    public int compareTo(Amount amount) {
        return this.value.compareTo(amount.value);
    }

    private static BigDecimal multiplyWithRounding(BigDecimal a, BigDecimal b) {
        return a.multiply(b).setScale(a.scale(), RoundingMode.HALF_UP);
    }

    public BigDecimal getValue() {
        return value;
    }
}