package org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare;

import org.manmvou.mandaloreexpress.booking.domain.tax.TaxPortion;

import java.util.UUID;

public class SelectedFare {
    private final UUID id;
    private final ComfortClass comfortClass;
    private final Price price;

    public SelectedFare(ComfortClass comfortClass, Price price) {
        this.id = UUID.randomUUID();
        this.comfortClass = comfortClass;
        this.price = price;
    }

    public SelectedFare(UUID id, ComfortClass comfortClass, Price price) {
        this.id = id;
        this.comfortClass = comfortClass;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public ComfortClass getComfortClass() {
        return comfortClass;
    }

    public Price getPrice() {
        return price;
    }

    public TaxPortion getTaxPortion() {
        return price.getTaxPortionOf(comfortClass.getTaxRate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectedFare that = (SelectedFare) o;
        return id.equals(that.id) &&
                comfortClass == that.comfortClass &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + comfortClass.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SelectedFare{" +
                "id=" + id +
                ", comfortClass=" + comfortClass +
                ", price=" + price +
                '}';
    }
}
