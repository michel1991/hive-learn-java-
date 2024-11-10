package org.manmvou.mandalore.express.search.domain.spacetrain;

import java.time.Duration;
import java.time.LocalDateTime;

public class Schedule {
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final Duration duration;

    public Schedule(LocalDateTime departure, LocalDateTime arrival) {
        if (departure.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("departure cannot be in the past");
        }
        if (arrival.isBefore(departure)) {
            throw new IllegalArgumentException("arrival cannot precede the departure");
        }
        this.departure = departure;
        this.arrival = arrival;
        this.duration = Duration.between(departure, arrival);
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public Duration getDuration() {
        return duration;
    }
}

