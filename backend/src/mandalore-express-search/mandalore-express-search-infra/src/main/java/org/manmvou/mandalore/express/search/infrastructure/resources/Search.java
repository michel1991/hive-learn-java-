package org.manmvou.mandalore.express.search.infrastructure.resources;

import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;
import org.springframework.hateoas.RepresentationModel;
import java.util.UUID;

@Resource
public class Search extends RepresentationModel<Search> {

    private final UUID id;
    private final Criteria criteria;

    public Search(UUID id, Criteria criteria) {
        this.id = id;
        this.criteria = criteria;
    }

    public UUID getId() {
        return id;
    }

    public Criteria getCriteria() {
        return criteria;
    }
}
