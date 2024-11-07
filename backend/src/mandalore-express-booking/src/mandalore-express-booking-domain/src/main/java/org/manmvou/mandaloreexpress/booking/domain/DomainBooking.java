package org.manmvou.mandaloreexpress.booking.domain;

import org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain;

import java.util.List;
import java.util.UUID;

public class DomainBooking extends Booking{

    public DomainBooking(List<SpaceTrain> spaceTrains) {
        super(spaceTrains);
    }

    public DomainBooking(UUID id, List<SpaceTrain> spaceTrains, boolean finalized) {
        super(id, spaceTrains, finalized);
    }
}
