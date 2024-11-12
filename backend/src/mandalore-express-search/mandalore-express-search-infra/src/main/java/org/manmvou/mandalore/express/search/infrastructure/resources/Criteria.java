package org.manmvou.mandalore.express.search.infrastructure.resources;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;

import java.util.List;

@Resource
public class Criteria {

    private final  List<Journey> journeys;
    @JsonCreator
    public Criteria( @JsonProperty("journeys") List<Journey> journeys) {
        this.journeys = journeys;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "journeys=" + journeys +
                '}';
    }
}
