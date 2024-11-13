package org.manmvou.mandalore.express.search.domain.criteria;
import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Journey {
    private String departureSpacePortId;
    private LocalDateTime departureSchedule;
    private String arrivalSpacePortId;

    public Journey(String departureSpacePortId, LocalDateTime departureSchedule, String arrivalSpacePortId) {
        if (!departureSchedule.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot create a Journey with a departure scheduled in the past");
        }
        this.departureSpacePortId = departureSpacePortId;
        this.departureSchedule = departureSchedule;
        this.arrivalSpacePortId = arrivalSpacePortId;
    }

    public boolean isConnectedTo(Journey nextJourney) {
        return this.arrivalSpacePortId.equals(nextJourney.departureSpacePortId);
    }

    public String getDepartureSpacePortId() {
        return departureSpacePortId;
    }

    public LocalDateTime getDepartureSchedule() {
        return departureSchedule;
    }

    public String getArrivalSpacePortId() {
        return arrivalSpacePortId;
    }


    @Override
    public String toString() {
        return "Journey{" +
                "departureSpacePortId='" + departureSpacePortId + '\'' +
                ", departureSchedule=" + departureSchedule +
                ", arrivalSpacePortId='" + arrivalSpacePortId + '\'' +
                '}';
    }

    public static class Journeys {
        private final List<Journey> journeys;

        public Journeys(List<Journey> trains) {
            this.journeys = trains;
        }

        public List<Journey> getJourneys() {
            return this.journeys;
        }

        @Override
        public String toString() {
            return "SpaceTrains{" +
                    "trains=" + journeys +
                    '}';
        }

        public boolean isEmpty() {
            return this.journeys.isEmpty();
        }

        public int size() {
            return this.journeys.size();
        }

        public Journey get(int i) {
            return this.journeys.get(i);
        }

        public static Journeys of(List<Journey> trains){
            return new Journeys(trains);
        }
    }
}
