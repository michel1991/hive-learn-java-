package org.manmvou.mandalore.express.search.infrastructure.resources;
import org.springframework.hateoas.RepresentationModel;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

import java.util.List;

@Resource
public class Selection extends RepresentationModel<Selection> {

    private final List<SelectedSpaceTrain> spaceTrains;
    private final Price totalPrice;

    public Selection(List<SelectedSpaceTrain> spaceTrains, Price totalPrice) {
        this.spaceTrains = spaceTrains;
        this.totalPrice = totalPrice;
    }

    public List<SelectedSpaceTrain> getSpaceTrains() {
        return spaceTrains;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }
}
