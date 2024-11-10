package org.manmvou.mandalore.express.booking.infrastructure.clients;

import org.manmvou.mandalore.express.annotations.ddd.AntiCorruptionLayer;
//import com.beyondxscratch.mandaloreexpress.booking.domain.spacetrain.SpaceTrain as BookingTrain;
import org.manmvou.mandalore.express.search.domain.api.GetSearch;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.ComfortClass;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.Price;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.SelectedFare;
import org.manmvou.mandaloreexpress.booking.domain.spi.IsSelectionComplete;
import org.manmvou.mandaloreexpress.booking.domain.spi.GetTrainsToBook;

/*import com.beyondxscratch.mandaloreexpress.search.domain.Search;
import com.beyondxscratch.mandaloreexpress.search.domain.api.GetSearch;
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.SpaceTrain as SearchTrain;
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.Schedule;
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.FareOption;*/
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@AntiCorruptionLayer
public class BookingInProcAdapterToSearch implements GetTrainsToBook, IsSelectionComplete{
    private final GetSearch getSearch;

    public BookingInProcAdapterToSearch(GetSearch getSearch) {
        this.getSearch = getSearch;
    }

    @Override
    public List<SpaceTrain> from(UUID searchId) {
        return List.of();
    }

    @Override
    public Boolean of(UUID searchId) {
        return null;
    }
}
