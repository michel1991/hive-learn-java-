package org.manmvou.mandalore.express.search.domain.spi;

import org.manmvou.mandalore.express.search.domain.Search;

import java.util.UUID;

public interface Searches {
    Search findSearchIdentifiedBy(UUID searchId);
    Search save(Search search);
}