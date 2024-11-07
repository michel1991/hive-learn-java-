package org.manmvou.mandalore.express.booking.infrastructure.resources;

//import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.SelectedFare;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain;
//import com.beyondxscratch.mandaloreexpress.booking.domain.spacetrain.SpaceTrain as DomainSpaceTrain;
//import com.beyondxscratch.mandaloreexpress.booking.domain.spacetrain.fare.SelectedFare;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

import java.time.LocalDateTime;
import java.util.List;

@Resource
public class SpaceTrainResource {

    private final String number;
    private final String originId;
    private final String destinationId;
    private final LocalDateTime departureSchedule;
    private final LocalDateTime arrivalSchedule;
    //private final SelectedFare selectedFare;
    private final SelectedFareResource selectedFare;

    public SpaceTrainResource(String number, String originId, String destinationId, LocalDateTime departureSchedule, LocalDateTime arrivalSchedule, SelectedFareResource selectedFare) {
        this.number = number;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureSchedule = departureSchedule;
        this.arrivalSchedule = arrivalSchedule;
        this.selectedFare = selectedFare;
    }

    public String getNumber() {
        return number;
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

    public SelectedFareResource getSelectedFare() {
        return selectedFare;
    }

    // Static conversion functions
    //PriceResource
    /*public static List<SpaceTrain> toResource(List<SpaceTrain> domainSpaceTrains) {
        return domainSpaceTrains.stream()
                .map(SpaceTrain::toResource)
                .collect(Collectors.toList());
    }*/

    /*public static List<SpaceTrain> toResource(List<PriceResource> domainSpaceTrains) {
        return domainSpaceTrains.stream()
                .map(SpaceTrain::toResource)
                .collect(Collectors.toList());
    }*/

    public static List<SpaceTrainResource> toResource(List<SpaceTrain> domainSpaceTrains) {
        /*return domainSpaceTrains.stream()
                .map(SpaceTrain::toResource)
                .collect(Collectors.toList());*/
        return null;
    }

    public static SpaceTrainResource toResource(SpaceTrain domainSpaceTrain) {
        /*return new SpaceTrainResource(
                domainSpaceTrain.getNumber(),
                domainSpaceTrain.getOriginId(),
                domainSpaceTrain.getDestinationId(),
                domainSpaceTrain.getSchedule().getDeparture(),
                domainSpaceTrain.getSchedule().getArrival(),
                null
                //domainSpaceTrain.getSelectedFare().toResource()
        );*/
        return null;
    }
}
