package org.manmvou.mandalore.express.search.domain.spacetrain.fare;

import org.manmvou.mandaloreexpress.money.domain.Amount;
import org.manmvou.mandaloreexpress.money.domain.Currency;

import java.util.Objects;

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
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}


