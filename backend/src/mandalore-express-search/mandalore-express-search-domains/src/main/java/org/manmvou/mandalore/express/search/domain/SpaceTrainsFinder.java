package org.manmvou.mandalore.express.search.domain;



//import com.beyondxscratch.ddd.DomainService;
// import com.beyondxscratch.mandaloreexpress.money.domain.Amount;
import org.manmvou.mandaloreexpress.money.domain.Amount;
//import com.beyondxscratch.mandaloreexpress.money.domain.Currency;
//import com.beyondxscratch.mandaloreexpress.money.domain.Currency;
import org.manmvou.mandaloreexpress.money.domain.Currency;
//import com.beyondxscratch.mandaloreexpress.search.domain.api.GetSearch;
//import org.manmvou.mandalore.express.search.domain.api.GetSearch;
import org.manmvou.mandalore.express.search.domain.api.*;
import org.manmvou.mandalore.express.search.domain.api.RetrieveSpacePorts;
import org.manmvou.mandalore.express.search.domain.api.SearchForSpaceTrains;
import org.manmvou.mandalore.express.search.domain.api.SelectSpaceTrain;
import org.manmvou.mandalore.express.search.domain.criteria.Criteria;
import org.manmvou.mandalore.express.search.domain.criteria.Journey;
import org.manmvou.mandalore.express.search.domain.criteria.Journey.Journeys;
import org.manmvou.mandalore.express.search.domain.spaceport.Planet;
import org.manmvou.mandalore.express.search.domain.spaceport.SpacePort;
import org.manmvou.mandalore.express.search.domain.spacetrain.Bound;
import org.manmvou.mandalore.express.search.domain.spacetrain.Schedule;
import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain;
import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain.SpaceTrains;
import org.manmvou.mandalore.express.search.domain.spacetrain.fare.ComfortClass;
import org.manmvou.mandalore.express.search.domain.spacetrain.fare.FareOption;
import org.manmvou.mandalore.express.search.domain.spacetrain.fare.Price;
import org.manmvou.mandalore.express.search.domain.spi.Searches;
import org.manmvou.mandalore.express.search.domain.spi.SpacePorts;
import org.manmvou.mandalore.express.annotations.ddd.DomainService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.manmvou.mandalore.express.search.domain.spacetrain.Bound.INBOUND;
import static org.manmvou.mandalore.express.search.domain.spacetrain.Bound.OUTBOUND;
//import static com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.ComfortClass.*;
import static org.manmvou.mandalore.express.search.domain.spacetrain.fare.ComfortClass.*;


@DomainService
public class SpaceTrainsFinder implements RetrieveSpacePorts, SearchForSpaceTrains, GetSearch, SelectSpaceTrain {

    private final SpacePorts spacePorts;
    private final Searches searches;

    public SpaceTrainsFinder(SpacePorts spacePorts, Searches searches) {
        this.spacePorts = spacePorts;
        this.searches = searches;
    }

    @Override
    public SpacePort identifiedBy(String id) {
        return spacePorts.getAllSpacePorts().stream()
                .filter(port -> port.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No spaceport found with ID " + id));
    }

    @Override
    public SpacePorts getSpacePorts() {
        return this.spacePorts;
    }

    @Override
    public Set<SpacePort> havingInTheirName(String partialName) {
        return spacePorts.getAllSpacePorts().stream()
                .filter(port -> port.hasNameContaining(partialName))
                .collect(Collectors.toSet());
    }

    @Override
    public Searches getSearches() {
        return this.searches;
    }

   /* private fun Journeys.mustNotStayOnTheSamePlanet(): Boolean {
        return none { spacePorts.find(it.departureSpacePortId).location == spacePorts.find(it.arrivalSpacePortId).location }
    }*/

    private Boolean mustNotStayOnTheSamePlanet(){
        /*return none { spacePorts.find(it.departureSpacePortId).location ==
        spacePorts.find(it.arrivalSpacePortId).location }*/
        //spacePorts.find()

        return true;
    }


    @Override
    public Search satisfying(Criteria criteria) {
        Journeys journeys = criteria.getJourneys();
        /*if (!journeys.mustNotStayOnTheSamePlanet(spacePorts)) {
            throw new IllegalArgumentException("Cannot perform a trip departing and arriving on the same Planet");
        }*/

        SpaceTrains spaceTrains = findMatchingSpaceTrainsFor(journeys);
        return searches.save(new Search(criteria, spaceTrains));
    }

    @Override
    public Search selectFareOfSpaceTrainInSearch(String spaceTrainNumber, UUID fareId, UUID searchId, boolean resetSelection) {
        Search search = Optional.ofNullable(searches.findSearchIdentifiedBy(searchId))
                .orElseThrow(() -> new NoSuchElementException("Unknown search id " + searchId));
        Search searchWithSelection = search.selectSpaceTrainWithFare(spaceTrainNumber, fareId, resetSelection);
        return searches.save(searchWithSelection);
    }

    private SpaceTrains findMatchingSpaceTrainsFor(Journeys journeys) {
        List<SpaceTrain> spaceTrains = new ArrayList<>();
        for (int i = 0; i < journeys.size(); i++) {
            Journey journey = journeys.get(i);
            Bound bound = Bound.fromJourneyIndex(i);
            long firstDepartureDeltaInMinutes = (long) (Math.random() * 61);
            for (int j = 1; j <= 5; j++) {
                spaceTrains.add(getSpaceTrain(journey, j, firstDepartureDeltaInMinutes, bound));
            }
        }
       // return computeCompatibilities(spaceTrains);
        return null;
    }

    private List<SpaceTrain> computeCompatibilities(List<SpaceTrain> spaceTrains) {
        /*List<SpaceTrain> inbounds = spaceTrains.stream().filter(st -> st.getBound() == INBOUND).collect(Collectors.toList());
        if (inbounds.isEmpty()) return spaceTrains;

        List<SpaceTrain> outbounds = spaceTrains.stream().filter(st -> st.getBound() == OUTBOUND).collect(Collectors.toList());
        List<String> inboundNumbers = inbounds.stream().map(SpaceTrain::getNumber).collect(Collectors.toList());

        List<SpaceTrain> outboundsWithCompatibilities = outbounds.stream().map(outbound ->
                        outbound.withCompatibleSpaceTrains(Set.of(inboundNumbers.get((int) (Math.random() * inboundNumbers.size())),
                                inboundNumbers.get((int) (Math.random() * inboundNumbers.size())))))
                .collect(Collectors.toList());

        List<SpaceTrain> inboundsWithCompatibilities = inbounds.stream().map(inbound ->
                        inbound.withCompatibleSpaceTrains(outboundsWithCompatibilities.stream()
                                .filter(outbound -> outbound.getCompatibleSpaceTrains().contains(inbound.getNumber()))
                                .map(SpaceTrain::getNumber)
                                .collect(Collectors.toSet())))
                .filter(st -> !st.getCompatibleSpaceTrains().isEmpty())
                .collect(Collectors.toList());

        List<SpaceTrain> combined = new ArrayList<>(outboundsWithCompatibilities);
        combined.addAll(inboundsWithCompatibilities);
        return combined;*/
        return new ArrayList<>();
    }

    private SpaceTrain getSpaceTrain(Journey journey,
                                     int spaceTrainIndex,
                                     long firstDepartureDeltaInMinutes,
                                     Bound bound) {
        /*LocalDateTime departure = computeDepartureSchedule(
                journey.getDepartureSchedule(),
                spaceTrainIndex,
                firstDepartureDeltaInMinutes);
        SpacePort arrivalSpacePort = spacePorts.find(journey.getArrivalSpacePortId());
        return new SpaceTrain(
                computeSpaceTrainNumber(arrivalSpacePort.getLocation(), spaceTrainIndex),
                bound,
                journey.getDepartureSpacePortId(),
                journey.getArrivalSpacePortId(),
                new Schedule(
                        departure, computeArrival(departure, (long) spaceTrainIndex)
                ),
                computeFares(),
                Set.of()
        );*/

        return null;
    }

    private LocalDateTime computeDepartureSchedule(LocalDateTime criteriaDepartureSchedule, int spaceTrainIndex, long firstDepartureDeltaInMinutes) {
        return criteriaDepartureSchedule.plusMinutes(firstDepartureDeltaInMinutes).plusHours(2L * (spaceTrainIndex - 1));
    }

    private String computeSpaceTrainNumber(Planet arrivalLocation, int spaceTrainIndex) {
        return arrivalLocation.name().substring(0, 5) + spaceTrainIndex + ((int) (Math.random() * 90 + 10));
    }

    private LocalDateTime computeArrival(LocalDateTime departureSchedule, long spaceTrainIndex) {
        return departureSchedule.plusHours(97 + spaceTrainIndex).plusMinutes((long) (Math.random() * 821 + 20));
    }

    private Set<FareOption> computeFares() {
        return Set.of(computeFare(FIRST), computeFare(SECOND));
    }

    private FareOption computeFare(ComfortClass comfortClass) {
        int amount = comfortClass == FIRST ? (int) (Math.random() * 221 + 180) : (int) (Math.random() * 51 + 150);
        return new FareOption(UUID.randomUUID(), comfortClass, new Price(new Amount(new BigDecimal(amount)), Currency.REPUBLIC_CREDIT));
    }
}
