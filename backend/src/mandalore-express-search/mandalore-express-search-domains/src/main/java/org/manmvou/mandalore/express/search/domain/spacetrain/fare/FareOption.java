package org.manmvou.mandalore.express.search.domain.spacetrain.fare;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Set;

public class FareOption {
    private final UUID id;
    private final ComfortClass comfortClass;
    private final Price price;

    // Constructor with generated ID
    public FareOption(ComfortClass comfortClass, Price price) {
        this.id = UUID.randomUUID();
        this.comfortClass = comfortClass;
        this.price = price;
    }

    // Constructor with provided ID
    public FareOption(UUID id, ComfortClass comfortClass, Price price) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FareOption that = (FareOption) o;
        return id.equals(that.id) &&
                comfortClass == that.comfortClass &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comfortClass, price);
    }

    @Override
    public String toString() {
        return "FareOption{" +
                "id=" + id +
                ", comfortClass=" + comfortClass +
                ", price=" + price +
                '}';
    }

    // // FareOptions type alias equivalent
    public static class FareOptionsTypeAlias {
        private final Set<FareOption> options;

        public FareOptionsTypeAlias(Set<FareOption> options) {
            this.options = options;
        }

        public Set<FareOption> getOptions() {
            return options;
        }

        @Override
        public String toString() {
            return "FareOptions{" +
                    "options=" + options +
                    '}';
        }
    }
}

// FareOptions type alias equivalent



