package org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrossDomainHypermediaResolver {

    private final List<HypermediaResolver> hypermediaResolvers;

    public CrossDomainHypermediaResolver(List<HypermediaResolver> hypermediaResolvers) {
        this.hypermediaResolvers = hypermediaResolvers;
    }

    public Link resolveLinkFor(Relation relation) {
        return hypermediaResolvers.stream()
                .filter(resolver -> resolver.accept(relation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No suitable HypermediaResolver found for relation"))
                .linkFor(relation);
    }
}