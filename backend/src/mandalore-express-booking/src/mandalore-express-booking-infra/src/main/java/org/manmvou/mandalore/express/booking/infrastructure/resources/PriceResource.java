package org.manmvou.mandalore.express.booking.infrastructure.resources;

import org.manmvou.mandaloreexpress.money.domain.Currency;
//import org.manmvou.mandaloreexpress.infrastructure.Resource;
import java.math.BigDecimal;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.Price;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

// ok for this class
@Resource
public class PriceResource {
    private final BigDecimal amount;
    private final Currency currency;

    public PriceResource(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    // Conversion function
    public static PriceResource toResource(Price domainPrice) {
        return new PriceResource(domainPrice.getAmount().getValue(), domainPrice.getCurrency());
        //return null;
    }
}
