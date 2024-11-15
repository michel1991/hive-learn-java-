package org.manmvou.mandalore.express.search.infrastructure.resources;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

import org.manmvou.mandalore.express.search.domain.spacetrain.Bound;
import org.springframework.hateoas.RepresentationModel;
import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;

@Resource
public class SpaceTrain extends RepresentationModel<SpaceTrain> {

    private final String number;
    private final Bound bound;
    private final String originId;
    private final String destinationId;
    private final LocalDateTime departureSchedule;
    private final LocalDateTime arrivalSchedule;
    private final Duration duration;
    private final FareOption.FareOptions fareOptions;

    public SpaceTrain(String number,
                      Bound bound,
                      String originId,
                      String destinationId,
                      LocalDateTime departureSchedule,
                      LocalDateTime arrivalSchedule,
                      Duration duration,
                      FareOption.FareOptions fareOptions) {
        this.number = number;
        this.bound = bound;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureSchedule = departureSchedule;
        this.arrivalSchedule = arrivalSchedule;
        this.duration = duration;
        this.fareOptions = fareOptions;
    }

    // Getters for each field
    public String getNumber() { return number; }
    public Bound getBound() { return bound; }
    public String getOriginId() { return originId; }
    public String getDestinationId() { return destinationId; }
    public LocalDateTime getDepartureSchedule() { return departureSchedule; }
    public LocalDateTime getArrivalSchedule() { return arrivalSchedule; }
    public Duration getDuration() { return duration; }
    public Set<FareOption> getFareOptions() { return fareOptions.getFareOptions(); }

    @Resource
    public static  class SpaceTrains extends RepresentationModel<SpaceTrains> {

        private final List<SpaceTrain> spaceTrains;

        public SpaceTrains(List<SpaceTrain> spaceTrains) {
            this.spaceTrains = spaceTrains;
        }

        public List<SpaceTrain> getSpaceTrains() {
            return spaceTrains;
        }
    }
}



