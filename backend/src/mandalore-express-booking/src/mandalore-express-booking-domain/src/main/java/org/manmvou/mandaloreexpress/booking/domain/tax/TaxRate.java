package org.manmvou.mandaloreexpress.booking.domain.tax;

import java.math.BigDecimal;

public class TaxRate {
    private final BigDecimal value;

    public TaxRate(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("TaxRate must be strictly positive");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TaxRate{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxRate)) return false;
        TaxRate taxRate = (TaxRate) o;
        return value.equals(taxRate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}