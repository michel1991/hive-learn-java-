package org.manmvou.mandaloreexpress.booking.domain.tax;

import org.manmvou.mandaloreexpress.money.domain.Amount;
import org.manmvou.mandaloreexpress.money.domain.Currency;

public class TaxPortion {
    private final Amount amount;
    private final Currency currency;

    public TaxPortion(Amount amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Amount getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    //
    public TaxPortion plus(TaxPortion anotherTaxPortion) {
        if (!this.currency.equals(anotherTaxPortion.currency)) {
            throw new IllegalArgumentException("Cannot sum tax portions with different currencies");
        }
        Amount newAmount = this.amount.plus(anotherTaxPortion.amount);
        return new TaxPortion(newAmount, this.currency);
    }

    @Override
    public String toString() {
        return "TaxPortion{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxPortion)) return false;
        TaxPortion that = (TaxPortion) o;
        return amount.equals(that.amount) && currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}
