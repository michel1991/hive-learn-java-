package org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare;

import org.manmvou.mandaloreexpress.booking.domain.tax.TaxPortion;
import org.manmvou.mandaloreexpress.booking.domain.tax.TaxRate;
import org.manmvou.mandaloreexpress.money.domain.Amount;
import org.manmvou.mandaloreexpress.money.domain.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Price {
    private final Amount amount;
    private final Currency currency;

    public Price(Amount amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Price plus(Price anotherPrice) {
        if (!currency.equals(anotherPrice.currency)) {
            throw new IllegalArgumentException("Cannot sum prices with different currencies");
        }
        return new Price(amount.plus(anotherPrice.amount), currency);
    }

    public TaxPortion getTaxPortionOf(TaxRate taxRate) {
        BigDecimal taxRatio = BigDecimal.ONE.subtract(
                BigDecimal.ONE.divide(BigDecimal.ONE.add(taxRate.getValue()), 50, RoundingMode.HALF_UP)
        );
        return new TaxPortion(amount.times(taxRatio), currency);
    }

    public Amount getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return amount.equals(price.amount) && currency.equals(price.currency);
    }

    @Override
    public int hashCode() {
        return 31 * amount.hashCode() + currency.hashCode();
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
