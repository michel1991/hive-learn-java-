package org.manmvou.mandalore.express.search.domain.api;


import org.manmvou.mandalore.express.search.domain.Search;
import org.manmvou.mandalore.express.search.domain.criteria.Criteria;
import org.manmvou.mandalore.express.search.domain.spi.Searches;

public interface SearchForSpaceTrains {
    Searches getSearches(); //   val searches: Searches
    Search satisfying(Criteria criteria);
}