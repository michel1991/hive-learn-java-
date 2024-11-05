package org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare;

import org.manmvou.mandaloreexpress.booking.domain.tax.TaxRate;

import java.math.BigDecimal;

public enum ComfortClass {
    FIRST(new TaxRate(new BigDecimal("0.2"))),
    SECOND(new TaxRate(new BigDecimal("0.1")));

    private final TaxRate taxRate;

    ComfortClass(TaxRate taxRate) {
        this.taxRate = taxRate;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }
}