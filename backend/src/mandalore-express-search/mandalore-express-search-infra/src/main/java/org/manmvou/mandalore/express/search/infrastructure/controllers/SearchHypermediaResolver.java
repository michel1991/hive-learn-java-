package org.manmvou.mandalore.express.search.infrastructure.controllers;
import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.HypermediaResolver;
import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.Relation;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.lang.IllegalArgumentException;

@Component
public class SearchHypermediaResolver implements HypermediaResolver {
    @Override
    public boolean accept(Relation relation) {
        return false;
    }

    @Override
    public Link linkFor(Relation relation) {
        throw new IllegalArgumentException("SearchHypermediaResolver does not provide links for " + relation.getClass().getSimpleName() + " relation");
    }
}
