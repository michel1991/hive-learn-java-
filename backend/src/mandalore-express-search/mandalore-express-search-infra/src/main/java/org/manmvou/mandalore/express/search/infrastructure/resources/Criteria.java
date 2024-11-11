package org.manmvou.mandalore.express.search.infrastructure.resources;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

import java.util.List;

@Resource
public class Criteria {
    private final List<Journey> journeys;

    public Criteria(List<Journey> journeys) {
        this.journeys = journeys;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }
}
