package org.manmvou.mandalore.express.search.domain.criteria;
import org.manmvou.mandalore.express.search.domain.criteria.Journey.Journeys;
import java.time.LocalDateTime;
import java.util.List;

public class Criteria {

    private final Journey.Journeys journeys;

    public Criteria(Journey.Journeys journeys) {
        if (journeys.isEmpty()) {
            throw new IllegalArgumentException("Criteria must contain at least one journey");
        }
        if (!areOrderedByDepartureSchedule(journeys)) {
            throw new IllegalArgumentException("Criteria must only have journeys ordered by departure schedule");
        }
        if (!areConnectedTogether(journeys)) {
            throw new IllegalArgumentException("Criteria must only have connected journeys");
        }
        if (!haveAtLeastADayBetweenEach(journeys)) {
            throw new IllegalArgumentException("An elapse time of 5 days must be respected between journeys");
        }

        this.journeys = journeys;
    }

    public Journey.Journeys getJourneys() {
        return journeys;
    }

    private boolean areConnectedTogether(Journey.Journeys journeys) {
        for (int i = 0; i < journeys.size() - 1; i++) {
            if (!journeys.get(i).isConnectedTo(journeys.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "journeys=" + journeys +
                '}';
    }

    private boolean areOrderedByDepartureSchedule(Journey.Journeys journeys) {
        for (int i = 0; i < journeys.size() - 1; i++) {
            if (journeys.get(i).getDepartureSchedule().isAfter(journeys.get(i + 1).getDepartureSchedule())) {
                return false;
            }
        }
        return true;
    }

    private boolean isAtLeast5DaysAfter(LocalDateTime departureOfNextJourney, LocalDateTime departureOfPreviousJourney) {
        return !departureOfNextJourney.isBefore(departureOfPreviousJourney.plusDays(5));
    }

    private boolean haveAtLeastADayBetweenEach(Journey.Journeys journeys) {
        for (int i = 0; i < journeys.size() - 1; i++) {
            if (!isAtLeast5DaysAfter(journeys.get(i + 1).getDepartureSchedule(), journeys.get(i).getDepartureSchedule())) {
                return false;
            }
        }
        return true;
    }

    public boolean isOneWay(){
        return this.journeys.getJourneys().size()  == 1;
    }


}
