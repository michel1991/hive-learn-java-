package org.manmvou.mandalore.express.booking.infrastructure.resources;

import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.ComfortClass;
//import com.beyondxscratch.mandaloreexpress.infrastructure.Resource;
import org.springframework.hateoas.RepresentationModel;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.SelectedFare;
import java.util.UUID;

@Resource
public class SelectedFareResource  extends RepresentationModel<SelectedFareResource>{
    private final UUID id;
    private final ComfortClass comfortClass;
    private final PriceResource price;

    public SelectedFareResource(UUID id, ComfortClass comfortClass, PriceResource price) {
        this.id = id;
        this.comfortClass = comfortClass;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public ComfortClass getComfortClass() {
        return comfortClass;
    }

    public PriceResource getPrice() {
        return price;
    }

    // Conversion function
    public static SelectedFareResource toResource(SelectedFare domainSelectedFare) {
        /*return new SelectedFare(
                domainSelectedFare.getId(),
                domainSelectedFare.getComfortClass(),
                Price.toResource(domainSelectedFare.getPrice())
        );*/
        return new SelectedFareResource(
                domainSelectedFare.getId(),
                domainSelectedFare.getComfortClass(),
                null
          );
    }
}
