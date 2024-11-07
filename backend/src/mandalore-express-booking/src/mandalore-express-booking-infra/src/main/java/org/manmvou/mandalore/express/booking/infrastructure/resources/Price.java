package org.manmvou.mandalore.express.booking.infrastructure.resources;
import org.manmvou.mandaloreexpress.money.domain.Currency;

import java.math.BigDecimal;
public class Price {
    private final BigDecimal amount;
    private final Currency currency;

    public Price(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public static Price toResource(org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.Price domainPrice) {
        return new Price(domainPrice.getAmount().getValue(), domainPrice.getCurrency());
    }
}



