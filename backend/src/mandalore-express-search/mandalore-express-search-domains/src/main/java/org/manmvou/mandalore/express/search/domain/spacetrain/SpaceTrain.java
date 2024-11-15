package org.manmvou.mandalore.express.search.domain.spacetrain;

import org.manmvou.mandalore.express.search.domain.spacetrain.fare.FareOption;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

public class SpaceTrain {
    private final String number;
    private final Bound bound;
    private final String originId;
    private final String destinationId;
    private final Schedule schedule;
    private final FareOption.FareOptions fareOptions;
    private final Set<String> compatibleSpaceTrains;

    public SpaceTrain(String number,
                      Bound bound,
                      String originId,
                      String destinationId,
                      Schedule schedule,
                      FareOption.FareOptions fareOptions,
                      Set<String> compatibleSpaceTrains
    ) {
        this.number = number;
        this.bound = bound;
        this.originId = originId;
        this.destinationId = destinationId;
        this.schedule = schedule;
        this.fareOptions = fareOptions;
        this.compatibleSpaceTrains = compatibleSpaceTrains;

        if (fareOptions.getOptions().isEmpty()) {
            throw new IllegalArgumentException("SpaceTrain must have at least one fare option");
        }

        if (!isNotCompatibleWithItself()) {
            throw new IllegalArgumentException("SpaceTrain cannot be compatible with itself");
        }
    }

    public String getNumber() {
        return number;
    }

    public Bound getBound() {
        return bound;
    }

    public String getOriginId() {
        return originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public FareOption.FareOptions getFareOptions() {
        return fareOptions;
    }

    public Set<String> getCompatibleSpaceTrains() {
        return compatibleSpaceTrains;
    }

    public FareOption getFare(UUID fareId) {
        return fareOptions.getOptions().stream()
                .filter(fareOption -> fareOption.getId().equals(fareId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Fare with ID " + fareId + " not found"));
    }

    private boolean isNotCompatibleWithItself() {
        return !compatibleSpaceTrains.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceTrain that = (SpaceTrain) o;
        return Objects.equals(number, that.number) && bound == that.bound && Objects.equals(originId, that.originId) &&
                Objects.equals(destinationId, that.destinationId) && Objects.equals(schedule, that.schedule) &&
                Objects.equals(fareOptions, that.fareOptions) && Objects.equals(compatibleSpaceTrains, that.compatibleSpaceTrains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, bound, originId, destinationId, schedule, fareOptions, compatibleSpaceTrains);
    }

    @Override
    public String toString() {
        return "SpaceTrain{" +
                "number='" + number + '\'' +
                ", bound=" + bound +
                ", originId='" + originId + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", schedule=" + schedule +
                ", fareOptions=" + fareOptions +
                ", compatibleSpaceTrains=" + compatibleSpaceTrains +
                '}';
    }


    // SpaceTrains type alias equivalent

    public static class SpaceTrains {
        private final List<SpaceTrain> trains;

        public SpaceTrains(List<SpaceTrain> trains) {
            this.trains = trains;
        }

        public List<SpaceTrain> getTrains() {
            return trains;
        }

        public static List<SpaceTrain> get(List<SpaceTrain> spaceTrains, Bound bound) {
            return spaceTrains.stream()
                    .filter(spaceTrain -> spaceTrain.getBound() == bound)
                    .collect(Collectors.toList());
        }

            @Override
        public String toString() {
            return "SpaceTrains{" +
                    "trains=" + trains +
                    '}';
        }
    }
}


