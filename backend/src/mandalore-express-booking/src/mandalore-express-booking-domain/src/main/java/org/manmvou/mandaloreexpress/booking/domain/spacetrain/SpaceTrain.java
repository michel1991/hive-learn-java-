package org.manmvou.mandaloreexpress.booking.domain.spacetrain;


import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.SelectedFare;

public class SpaceTrain {
    private final String number;
    private final String originId;
    private final String destinationId;
    private final Schedule schedule;
    private final SelectedFare selectedFare;

    public SpaceTrain(String number, String originId, String destinationId, Schedule schedule, SelectedFare selectedFare) {
        this.number = number;
        this.originId = originId;
        this.destinationId = destinationId;
        this.schedule = schedule;
        this.selectedFare = selectedFare;
    }

    public String getNumber() {
        return number;
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

    public SelectedFare getSelectedFare() {
        return selectedFare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceTrain that = (SpaceTrain) o;
        return number.equals(that.number) &&
                originId.equals(that.originId) &&
                destinationId.equals(that.destinationId) &&
                schedule.equals(that.schedule) &&
                selectedFare.equals(that.selectedFare);
    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + originId.hashCode();
        result = 31 * result + destinationId.hashCode();
        result = 31 * result + schedule.hashCode();
        result = 31 * result + selectedFare.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SpaceTrain{" +
                "number='" + number + '\'' +
                ", originId='" + originId + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", schedule=" + schedule +
                ", selectedFare=" + selectedFare +
                '}';
    }
}
