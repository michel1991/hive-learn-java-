package org.manmvou.mandalore.express.search.domain;




//import com.beyondxscratch.mandaloreexpress.search.domain.criteria.Journeys;
//ok import com.beyondxscratch.mandaloreexpress.search.domain.selection.Selection;
//ok import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.Bound;
//ok import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.SpaceTrain;
//import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.SpaceTrains;
import org.manmvou.mandalore.express.search.domain.criteria.Criteria;
import org.manmvou.mandalore.express.search.domain.criteria.Journey;
import org.manmvou.mandalore.express.search.domain.selection.Selection;
//import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrains;
import org.manmvou.mandalore.express.search.domain.spacetrain.Bound;
//import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrains;
import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain.SpaceTrains;
import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain;
import static org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain.SpaceTrains.get;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Search {
    private final UUID id;
    private final Criteria criteria;
    private final SpaceTrains spaceTrains;
    private Selection selection;

    public Search(Criteria criteria, SpaceTrains spaceTrains) {
        this.id = UUID.randomUUID();
        this.criteria = criteria;
        this.spaceTrains = spaceTrains;
        //this.selection = selection != null ? selection : new Selection();
        this.selection = new Selection();

        validate();
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public List<SpaceTrain> fromBound(Bound bound){
        //this.spaceTrains.get()
        return SpaceTrain.SpaceTrains.get(spaceTrains.getTrains(), bound);
    }

    private void validate() {
        Journey.Journeys journeys = criteria.getJourneys();

        /*if (!areAllSatisfiedBySpaceTrain(journeys.getBounds())) {
            throw new IllegalArgumentException("Some journeys don't have at least one corresponding space train");
        }

        if (!correspondTo(journeys)) {
            throw new IllegalArgumentException("Some space trains don't correspond to any journey from the criteria");
        }

        if (!selectionExistsIn(spaceTrains)) {
            throw new IllegalArgumentException("Unknown space train in the selection");
        }

        if (!withOnlyKnownFaresFrom(spaceTrains)) {
            throw new IllegalArgumentException("Unknown fare in the selection");
        }

        if (!correspondsToTheBoundsOf(spaceTrains)) {
            throw new IllegalArgumentException("Selected space trains don't correspond to the right bound");
        }

        if (!spaceTrainsDontHaveCompatibilitiesWhenOneWay()) {
            throw new IllegalArgumentException("SpaceTrains cannot have compatibilities in a one way search");
        }

        if (!spaceTrainsHaveCompatibilitiesWhenRoundTrip()) {
            throw new IllegalArgumentException("SpaceTrains must have compatibilities in a round trip search");
        }

        if (!areNotCompatibleWithOtherSpaceTrainOnTheSameBound()) {
            throw new IllegalArgumentException("SpaceTrains cannot be compatible with another space train on the same bound");
        }

        if (!areCompatibleWithKnownOnes()) {
            throw new IllegalArgumentException("Some SpaceTrains have unknown compatibilities");
        }

        if (!haveSymmetricCompatibilities()) {
            throw new IllegalArgumentException("Some SpaceTrains don't respect a symmetric compatibility");
        }*/
    }

    public SpaceTrain getSpaceTrainWithNumber(String wantedNumber) {
        return spaceTrains.getTrains().stream()
                .filter(spaceTrain -> spaceTrain.getNumber().equals(wantedNumber))
                .findFirst()
                .orElseThrow();
    }

    public Search selectSpaceTrainWithFare(String spaceTrainNumber, UUID fareId, boolean resetSelection) {
        /*SpaceTrain spaceTrainToSelect = getSpaceTrainWithNumber(spaceTrainNumber);
        Selection newSelection = getExistingSelectionOrReset(resetSelection, spaceTrainToSelect);
        double price = spaceTrainToSelect.getFareOptions().stream()
                .filter(fareOption -> fareOption.getId().equals(fareId))
                .findFirst()
                .orElseThrow()
                .getPrice();
        return new Search(criteria,
                spaceTrains,
                newSelection.selectSpaceTrainWithFare(spaceTrainToSelect, fareId, price));*/
        return null;
    }

    public boolean isSelectionComplete() {
        return selection.getBounds().size() == criteria.getJourneys().size();
    }

    public List<SpaceTrain> selectableSpaceTrains(Bound bound) {
        if (selection.isEmpty()) {
            return get(spaceTrains.getTrains(), bound);
        } else {
            return selection.getSpaceTrainsByBound().stream()
                    .filter(entry -> entry.getKey() != bound)
                    .flatMap(
                            entry -> getSpaceTrainWithNumber(
                            entry.getValue().getSpaceTrainNumber()
                           )
                            .getCompatibleSpaceTrains().stream()
                    ).map(this::getSpaceTrainWithNumber)
                    .filter(spaceTrain -> spaceTrain.getBound() == bound)
                    .collect(Collectors.toList());
        }
    }

    private boolean spaceTrainsHaveCompatibilitiesWhenRoundTrip() {
        /*if (!criteria.isOneWay()) {
            return spaceTrains.stream().allMatch(spaceTrain -> !spaceTrain.getCompatibleSpaceTrains().isEmpty());
        }
        return true;*/
        return true;
    }

    private boolean spaceTrainsDontHaveCompatibilitiesWhenOneWay() {
        /*if (criteria.isOneWay()) {
            return spaceTrains.stream().allMatch(spaceTrain -> spaceTrain.getCompatibleSpaceTrains().isEmpty());
        }
        return true;*/
        return true;
    }

    private Selection getExistingSelectionOrReset(boolean resetSelection, SpaceTrain spaceTrainToSelect) {
        /*if (resetSelection) {
            return new Selection();
        } else {
            if (!spaceTrainToSelect.isCompatibleWithSelection(selection)) {
                throw new IllegalArgumentException("Cannot select incompatible space trains");
            }
            return selection;
        }*/
        return null;
    }

    private boolean selectionExistsIn(SpaceTrains spaceTrainsFromSearch) {
        /*return spaceTrainsFromSearch
                .stream().map(SpaceTrain::getNumber)
                .collect(Collectors.toList())
                .containsAll(selection.getSpaceTrains().stream().map(Selection::getSpaceTrainNumber)
                        .collect(Collectors.toList()));*/

        return true;
    }

    private boolean withOnlyKnownFaresFrom(SpaceTrains spaceTrainsFromSearch) {
        /*return selection.getSpaceTrains().stream().allMatch(selectedSpaceTrain ->
                spaceTrainsFromSearch.stream()
                        .filter(spaceTrain -> spaceTrain.getNumber().equals(selectedSpaceTrain.getSpaceTrainNumber()))
                        .anyMatch(spaceTrain -> spaceTrain.getFareOptions().stream().anyMatch(fareOption -> fareOption.getId().equals(selectedSpaceTrain.getFareId()))
                        )
        );*/
        return true;
    }

    /*private boolean correspondsToTheBoundsOf(SpaceTrains spaceTrainsFromSearch) {
       return selection.getSpaceTrains().stream().allMatch(spaceTrain ->
                spaceTrainsFromSearch.getTrains().stream()
                        .filter(spaceTrainFromSearch -> spaceTrainFromSearch.getNumber().equals(spaceTrain.getSpaceTrainNumber()))
                        .anyMatch(spaceTrainFromSearch -> spaceTrainFromSearch.getBound() == spaceTrain.getBound())
        );
        return true;
    }*/

    private boolean areAllSatisfiedBySpaceTrain(List<Bound> bounds) {
       //return spaceTrains.getTrains().isEmpty() || spaceTrains.stream().map(SpaceTrain::getBound).collect(Collectors.toList()).containsAll(bounds);
        return true;
    }

    private boolean correspondTo(Journey.Journeys journeys) {
        return spaceTrains.getTrains().stream().allMatch(spaceTrain ->
                journeys.getJourneys().stream().anyMatch(journey ->
                        journey.getDepartureSpacePortId().equals(spaceTrain.getOriginId()) &&
                                journey.getArrivalSpacePortId().equals(spaceTrain.getDestinationId())
                )
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Search)) return false;
        Search search = (Search) o;
        return id.equals(search.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    private boolean haveSymmetricCompatibilities() {
        /*return spaceTrains.stream().allMatch(spaceTrain ->
                spaceTrain.getCompatibleSpaceTrains().stream().allMatch(compatibleNumber ->
                        spaceTrains.stream().filter(st -> st.getNumber().equals(compatibleNumber)).findFirst().orElseThrow().getCompatibleSpaceTrains().contains(spaceTrain.getNumber())
                )
        );*/
        return true;
    }

    private boolean areCompatibleWithKnownOnes() {
        /*var spaceTrainByBound = spaceTrains.stream().collect(Collectors.groupingBy(SpaceTrain::getBound));

        List<SpaceTrain> outboundSpaceTrains = spaceTrainByBound.get(Bound.OUTBOUND);
        if (outboundSpaceTrains == null) return true;

        List<String> outboundSpaceTrainCompatibilities = outboundSpaceTrains.stream()
                .flatMap(spaceTrain -> spaceTrain.getCompatibleSpaceTrains().stream())
                .collect(Collectors.toList());

        List<SpaceTrain> inboundSpaceTrains = spaceTrainByBound.get(Bound.INBOUND);
        if (inboundSpaceTrains == null) return true;

        List<String> inboundSpaceTrainCompatibilities = inboundSpaceTrains.stream()
                .flatMap(spaceTrain -> spaceTrain.getCompatibleSpaceTrains().stream())
                .collect(Collectors.toList());

        return outboundSpaceTrainCompatibilities.stream().allMatch(outboundCompatibility ->
                inboundSpaceTrains.stream().anyMatch(inboundSpaceTrain -> outboundCompatibility.equals(inboundSpaceTrain.getNumber())
                )) && inboundSpaceTrainCompatibilities.stream().allMatch(inboundCompatibility ->
                outboundSpaceTrains.stream().anyMatch(outboundSpaceTrain -> inboundCompatibility.equals(outboundSpaceTrain.getNumber())
                ));*/
        return true;
    }

    private boolean areNotCompatibleWithOtherSpaceTrainOnTheSameBound() {
        /*return spaceTrains.stream().collect(Collectors.groupingBy(SpaceTrain::getBound)).values().stream()
                .allMatch(spaceTrainsOnTheSameBound ->
                        spaceTrainsOnTheSameBound.stream()
                                .flatMap(spaceTrain -> spaceTrain.getCompatibleSpaceTrains().stream())
                                .noneMatch(aCompatibleSpaceTrainNumber ->
                                        spaceTrainsOnTheSameBound.stream().anyMatch(spaceTrain -> spaceTrain.getNumber().equals(aCompatibleSpaceTrainNumber))
                                )
                );*/
        return true;
    }

    public UUID getId() {
        return id;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public SpaceTrains getSpaceTrains() {
        return spaceTrains;
    }

    public Selection getSelection() {
        return selection;
    }
}