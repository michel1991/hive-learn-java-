package org.manmvou.mandalore.express.search.infrastructure.resources;

import org.manmvou.mandaloreexpress.money.domain.Currency;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

import java.math.BigDecimal;

@Resource
public class Price {

    private final BigDecimal amount;
    private final Currency currency;

    public Price(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() { return amount; }
    public Currency getCurrency() { return currency; }

    // Static conversion method fromDomainPrice replace toResource
    public static Price toResource(org.manmvou.mandalore.express.search.domain.spacetrain.fare.Price domainPrice) {
        return new Price(domainPrice.getAmount().getValue(), domainPrice.getCurrency());
    }
}
