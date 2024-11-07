package org.manmvou.mandalore.express.booking.infrastructure.resources;

import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Resource
public class SpaceTrain {

    private String number;
    private String originId;
    private String destinationId;
    private LocalDateTime departureSchedule;
    private LocalDateTime arrivalSchedule;
    private SelectedFare selectedFare;

    public SpaceTrain(String number, String originId, String destinationId, LocalDateTime departureSchedule, LocalDateTime arrivalSchedule, SelectedFare selectedFare) {
        this.number = number;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureSchedule = departureSchedule;
        this.arrivalSchedule = arrivalSchedule;
        this.selectedFare = selectedFare;
    }

    public static List<SpaceTrain> toResource(List<org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain> spaceTrains) {
       return  SpaceTrainMapper.toResource(spaceTrains);
    }
}

class SpaceTrainMapper {
    public static List<SpaceTrain> toResource(List<org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain> domainSpaceTrains) {
        return domainSpaceTrains.stream()
                .map(SpaceTrainMapper::toResource)
                .collect(Collectors.toList());
    }

    public static SpaceTrain toResource(org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain domainSpaceTrain) {
        return new SpaceTrain(
                domainSpaceTrain.getNumber(),
                domainSpaceTrain.getOriginId(),
                domainSpaceTrain.getDestinationId(),
                domainSpaceTrain.getSchedule().getDeparture(),
                domainSpaceTrain.getSchedule().getArrival(),
                //domainSpaceTrain.getSelectedFare().toResource()
                SelectedFare.toResource(domainSpaceTrain.getSelectedFare())
        );
    }
}
