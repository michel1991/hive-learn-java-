package org.manmvou.mandalore.express.search.domain.criteria;

import java.time.LocalDateTime;
import java.util.List;

public class Criteria {

    private final List<Journey> journeys;

    public Criteria(List<Journey> journeys) {
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

    public List<Journey> getJourneys() {
        return journeys;
    }

    private boolean areConnectedTogether(List<Journey> journeys) {
        for (int i = 0; i < journeys.size() - 1; i++) {
            if (!journeys.get(i).isConnectedTo(journeys.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean areOrderedByDepartureSchedule(List<Journey> journeys) {
        /*for (int i = 0; i < journeys.size() - 1; i++) {
            if (journeys.get(i).getDepartureSchedule().isAfter(journeys.get(i + 1).getDepartureSchedule())) {
                return false;
            }
        }*/
        return true;
    }

    private boolean haveAtLeastADayBetweenEach(List<Journey> journeys) {
        /*for (int i = 0; i < journeys.size() - 1; i++) {
            if (!isAtLeast5DaysAfter(journeys.get(i + 1).getDepartureSchedule(), journeys.get(i).getDepartureSchedule())) {
                return false;
            }
        }*/
        return true;
    }

    private boolean isAtLeast5DaysAfter(LocalDateTime departureOfNextJourney, LocalDateTime departureOfPreviousJourney) {
        return !departureOfNextJourney.isBefore(departureOfPreviousJourney.plusDays(5));
    }
}
