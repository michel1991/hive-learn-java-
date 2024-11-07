package org.manmvou.mandalore.express.booking.infrastructure.resources;

import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.ComfortClass;
import org.springframework.hateoas.RepresentationModel;
import java.util.UUID;

@Resource
public class SelectedFare extends RepresentationModel<SelectedFare> {
    private final UUID id;
    private final ComfortClass comfortClass;
    private final Price price;

    public SelectedFare(UUID id, ComfortClass comfortClass, Price price) {
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

    public Price getPrice() {
        return price;
    }

    public static SelectedFare toResource(org.manmvou.mandaloreexpress.booking.domain.spacetrain.fare.SelectedFare domainSelectedFare) {
        return new SelectedFare(
                domainSelectedFare.getId(),
                domainSelectedFare.getComfortClass(),
                Price.toResource(domainSelectedFare.getPrice())
                //price.toResource()
        );
    }
}
