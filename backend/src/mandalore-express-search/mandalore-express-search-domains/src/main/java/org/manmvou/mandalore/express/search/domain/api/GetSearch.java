package org.manmvou.mandalore.express.search.domain.api;

import org.manmvou.mandalore.express.search.domain.Search;
import org.manmvou.mandalore.express.search.domain.spi.Searches;

import java.util.NoSuchElementException;
import java.util.UUID;

public interface GetSearch {
    Searches getSearches();

    default Search identifiedBy(UUID searchId) {
        Search foundSearch = getSearches().findSearchIdentifiedBy(searchId);
        if (foundSearch != null) {
            return foundSearch;
        } else {
            throw new NoSuchElementException("Unknown search " + searchId);
        }
    }
}