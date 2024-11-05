package org.manmvou.mandaloreexpress.booking.domain.spacetrain;

import java.time.Duration;
import java.time.LocalDateTime;

public class Schedule {
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final Duration duration;

    public Schedule(LocalDateTime departure, LocalDateTime arrival) {
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

    @Override
    public String toString() {
        return "Schedule{" +
                "departure=" + departure +
                ", arrival=" + arrival +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return departure.equals(schedule.departure) && arrival.equals(schedule.arrival);
    }

    @Override
    public int hashCode() {
        int result = departure.hashCode();
        result = 31 * result + arrival.hashCode();
        return result;
    }
}