package org.manmvou.mandalore.express.booking.infrastructure.resources;

//import org.manmvou.mandaloreexpress.infrastructure.Resource;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;
import org.springframework.hateoas.RepresentationModel;
import java.util.List;
import java.util.UUID;

// ok for this class
@Resource
public class Booking extends RepresentationModel<Booking> {

    private final UUID id;
    //private final List<SpaceTrainResource> spaceTrains;
    private final List<SpaceTrain> spaceTrains;
    //private final PriceResource totalPrice;
    private final Price totalPrice;

    /*public Booking(UUID id, List<SpaceTrainResource> spaceTrains, PriceResource totalPrice) {
        this.id = id;
        this.spaceTrains = spaceTrains;
        this.totalPrice = totalPrice;
    }*/

    public Booking(UUID id, List<SpaceTrain> spaceTrains, Price totalPrice) {
        this.id = id;
        this.spaceTrains = spaceTrains;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public List<SpaceTrain> getSpaceTrains() {
        return spaceTrains;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }
}
