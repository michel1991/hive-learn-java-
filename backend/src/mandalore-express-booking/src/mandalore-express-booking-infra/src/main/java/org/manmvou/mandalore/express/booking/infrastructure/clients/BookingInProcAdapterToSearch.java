package org.manmvou.mandalore.express.booking.infrastructure.clients;

import org.manmvou.mandalore.express.annotations.ddd.AntiCorruptionLayer;
//import com.beyondxscratch.mandaloreexpress.booking.domain.spacetrain.SpaceTrain as BookingTrain;
import org.manmvou.mandalore.express.search.domain.Search;
import org.manmvou.mandalore.express.search.domain.api.GetSearch;
//import org.manmvou.mandalore.express.search.domain.spacetrain.Schedule;
import org.manmvou.mandalore.express.search.domain.spacetrain.fare.FareOption;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.ComfortClass;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.Price;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.SelectedFare;
import org.manmvou.mandaloreexpress.booking.domain.spi.IsSelectionComplete;
import org.manmvou.mandaloreexpress.booking.domain.spi.GetTrainsToBook;
//import org.manmvou.mandaloreexpress.booking.domain.spacetrain.Schedule

/*import com.beyondxscratch.mandaloreexpress.search.domain.Search;
import com.beyondxscratch.mandaloreexpress.search.domain.api.GetSearch;
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.SpaceTrain as SearchTrain;
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.Schedule;
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.FareOption;*/
import java.util.*;
import java.util.stream.Collectors;

@AntiCorruptionLayer
public class BookingInProcAdapterToSearch implements GetTrainsToBook, IsSelectionComplete{
    private final GetSearch getSearch;

    public BookingInProcAdapterToSearch(GetSearch getSearch) {
        this.getSearch = getSearch;
    }

    @Override
    public List<SpaceTrain> from(UUID searchId) {
        Search search = getSearch.identifiedBy(searchId);
        getSelectionSortedByBound(search);
        //return convertSelectedTrainsFrom(search.getSelectionSortedByBound());
        return new ArrayList<>();
    }

    /*private fun Search.getSelectionSortedByBound() =
            selection.spaceTrainsByBound.sortedBy { it.key.ordinal }
            .map {
        Pair(
                getSpaceTrainWithNumber(it.value.spaceTrainNumber),
                it.value.fareId
        )
    }*/

    /*
        chat gpt
       private List<Pair<SpaceTrain, UUID>> getSelectionSortedByBound(Search search) {
        return search.getSelection().getSpaceTrainsByBound().entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().ordinal()))
                .map(entry -> new Pair<>(
                        getSpaceTrainWithNumber(entry.getValue().getSpaceTrainNumber()),
                        entry.getValue().getFareId()
                ))
                .collect(Collectors.toList());
    }*/

    private static  List<Pair<SpaceTrain, UUID>> getSelectionSortedByBound(Search search) {
       /* return search.getSelection().getSpaceTrainsByBound().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().ordinal()))
                .map(entry -> new Pair<>(
                        getSpaceTrainWithNumber(entry.getValue().getSpaceTrainNumber()),
                        entry.getValue().getFareId()
                ))
                .collect(Collectors.toList());*/
        return null;
    }




    /*private List<SpaceTrain> convertSelectedTrainsFrom(List<Pair<SearchTrain, UUID>> selection) {
        return selection.stream()
                .map(selectedTrain -> {
                    SearchTrain train = selectedTrain.getFirst();
                    UUID fareId = selectedTrain.getSecond();
                    FareOption selectedFareOption = train.getFare(fareId);

                    return new SpaceTrain(
                            train.getNumber(),
                            train.getOriginId(),
                            train.getDestinationId(),
                            convertToBookingSchedule(train.getSchedule()),
                            convertToBookingFare(selectedFareOption)
                    );
                })
                .collect(Collectors.toList());
    }*/

    @Override
    public boolean of(UUID searchId) {
        Search search = getSearch.identifiedBy(searchId);
        return search.isSelectionComplete();
    }

    // com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.Schedule
    private org.manmvou.mandaloreexpress.booking.domain.spacetrain.Schedule convertToBookingSchedule(
            org.manmvou.mandalore.express.search.domain.spacetrain.Schedule schedule
    ) {
        return new org.manmvou.mandaloreexpress.booking.domain.spacetrain.Schedule(
                schedule.getDeparture(), schedule.getArrival()
        );
    }

    private SelectedFare convertToBookingFare(FareOption fare) {
        return new SelectedFare(
                fare.getId(),
                ComfortClass.valueOf(fare.getComfortClass().name()),
                new Price(fare.getPrice().getAmount(), fare.getPrice().getCurrency())
        );
    }
}
