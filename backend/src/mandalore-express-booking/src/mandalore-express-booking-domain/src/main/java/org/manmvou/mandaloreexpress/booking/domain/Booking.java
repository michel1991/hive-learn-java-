package org.manmvou.mandaloreexpress.booking.domain;

import org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.Price;
import org.manmvou.mandaloreexpress.booking.domain.tax.TaxPortion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final UUID id;
    private final List<SpaceTrain> spaceTrains;
    private final boolean finalized;

    public Booking(List<SpaceTrain> spaceTrains) {
        this(UUID.randomUUID(), spaceTrains, false);
    }

    public Booking(UUID id, List<SpaceTrain> spaceTrains, boolean finalized) {
        if (spaceTrains.isEmpty()) {
            throw new IllegalArgumentException("Cannot book nothing");
        }
        this.id = id;
        this.spaceTrains = spaceTrains;
        this.finalized = finalized;

        if (!hasNoDepartureInThePastWhenNewBooking()) {
            throw new IllegalArgumentException("SpaceTrains cannot depart in the past for a new Booking");
        }
    }

    public UUID getId() {
        return id;
    }

    public List<SpaceTrain> getSpaceTrains() {
        return spaceTrains;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public Price getTotalPrice() {
        return spaceTrains.stream()
                .map(train -> train.getSelectedFare().getPrice())
                .reduce(Price::plus)
                .orElseThrow(() -> new IllegalStateException("No prices available"));
    }

    public TaxPortion getTaxPortion() {
        return spaceTrains.stream()
                .map(train -> train.getSelectedFare().getTaxPortion())
                .reduce(TaxPortion::plus)
                .orElseThrow(() -> new IllegalStateException("No tax portions available"));
    }

    private boolean hasNoDepartureInThePastWhenNewBooking() {
        return finalized || spaceTrains.stream()
                .allMatch(train -> train.getSchedule().getDeparture().isAfter(LocalDateTime.now()));
    }

    // finalizeBooking == finalize
    public Booking finalizeBooking() {
        return new Booking(id, spaceTrains, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", spaceTrains=" + spaceTrains +
                ", finalized=" + finalized +
                '}';
    }
}
