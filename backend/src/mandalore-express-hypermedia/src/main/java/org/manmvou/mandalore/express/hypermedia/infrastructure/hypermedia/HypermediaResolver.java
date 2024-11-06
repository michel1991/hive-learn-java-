package org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia;

import org.springframework.hateoas.Link;

public interface HypermediaResolver {

    boolean accept(Relation relation);

    Link linkFor(Relation relation);
}

