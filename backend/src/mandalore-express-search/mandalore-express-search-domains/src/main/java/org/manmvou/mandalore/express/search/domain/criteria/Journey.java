package org.manmvou.mandalore.express.search.domain.criteria;
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

    public List<Journey> getAliasTypeJourney(){
        List<Journey> journeys = new ArrayList<>();
        return journeys;
    }
}
