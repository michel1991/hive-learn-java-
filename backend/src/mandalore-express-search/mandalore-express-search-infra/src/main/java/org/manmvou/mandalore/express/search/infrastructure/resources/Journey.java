package org.manmvou.mandalore.express.search.infrastructure.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;
 //import org.manmvou.mandalore.express.search.domain.criteria.Journey;

@Resource
public class Journey {
    private final URI departureSpacePortId;
    private final String departureSchedule;
    private final URI arrivalSpacePortId;

    public Journey(URI departureSpacePortId, String departureSchedule, URI arrivalSpacePortId) {
        this.departureSpacePortId = departureSpacePortId;
        this.departureSchedule = departureSchedule;
        this.arrivalSpacePortId = arrivalSpacePortId;
    }

    public URI getDepartureSpacePortId() {
        return departureSpacePortId;
    }

    public String getDepartureSchedule() {
        return departureSchedule;
    }

    public URI getArrivalSpacePortId() {
        return arrivalSpacePortId;
    }

    // Conversion from DomainJourney to Journey resource
    public static Journey toResource(org.manmvou.mandalore.express.search.domain.criteria.Journey domainJourney) {
        URI departure = URI.create(domainJourney.getDepartureSpacePortId());
        URI arrival = URI.create(domainJourney.getArrivalSpacePortId());
        String departureSchedule = domainJourney.getDepartureSchedule().toString();
        return new Journey(departure, departureSchedule, arrival);
    }

    public static class Journeys extends ArrayList<Journey>{
        public Journeys(Collection<? extends Journey> c) {
            super(c);
        }

        public static List<Journey> toResource(org.manmvou.mandalore.express.search.domain.criteria.Journey.Journeys domainJourneys) {
            return domainJourneys.getJourneys().stream()
                    .map(Journey::toResource)
                    .collect(Collectors.toList());
        }

    }
}
