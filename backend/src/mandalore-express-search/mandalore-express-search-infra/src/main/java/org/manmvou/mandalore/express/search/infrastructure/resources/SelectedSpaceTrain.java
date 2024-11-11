package org.manmvou.mandalore.express.search.infrastructure.resources;
import org.manmvou.mandalore.express.search.domain.spacetrain.Bound;
import org.springframework.hateoas.RepresentationModel;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

//import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.Bound;

import java.time.LocalDateTime;

@Resource
public class SelectedSpaceTrain extends RepresentationModel<SelectedSpaceTrain> {

    private final String number;
    private final Bound bound;
    private final String originId;
    private final String destinationId;
    private final LocalDateTime departureSchedule;
    private final LocalDateTime arrivalSchedule;
    private final FareOption fareOption;

    public SelectedSpaceTrain(
            String number,
            Bound bound,
            String originId,
            String destinationId,
            LocalDateTime departureSchedule,
            LocalDateTime arrivalSchedule,
            FareOption fareOption) {
        this.number = number;
        this.bound = bound;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureSchedule = departureSchedule;
        this.arrivalSchedule = arrivalSchedule;
        this.fareOption = fareOption;
    }

    public String getNumber() {
        return number;
    }

    public Bound getBound() {
        return bound;
    }

    public String getOriginId() {
        return originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public LocalDateTime getDepartureSchedule() {
        return departureSchedule;
    }

    public LocalDateTime getArrivalSchedule() {
        return arrivalSchedule;
    }

    public FareOption getFareOption() {
        return fareOption;
    }
}
