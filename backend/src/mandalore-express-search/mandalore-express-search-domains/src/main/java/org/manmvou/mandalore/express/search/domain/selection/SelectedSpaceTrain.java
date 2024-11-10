package org.manmvou.mandalore.express.search.domain.selection;

import org.manmvou.mandalore.express.search.domain.spacetrain.fare.Price;

import java.util.Objects;
import java.util.UUID;

public class SelectedSpaceTrain {
    private final String spaceTrainNumber;
    private final UUID fareId;
    private final Price price;

    public SelectedSpaceTrain(String spaceTrainNumber, UUID fareId, Price price) {
        this.spaceTrainNumber = spaceTrainNumber;
        this.fareId = fareId;
        this.price = price;
    }

    public String getSpaceTrainNumber() {
        return spaceTrainNumber;
    }

    public UUID getFareId() {
        return fareId;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SelectedSpaceTrain{" +
                "spaceTrainNumber='" + spaceTrainNumber + '\'' +
                ", fareId=" + fareId +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectedSpaceTrain that = (SelectedSpaceTrain) o;
        return spaceTrainNumber.equals(that.spaceTrainNumber) &&
                fareId.equals(that.fareId) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceTrainNumber, fareId, price);
    }
}
