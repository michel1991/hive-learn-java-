package org.manmvou.mandalore.express.search.domain.stubs;




import org.manmvou.mandalore.express.annotations.ddd.Stub;
import org.manmvou.mandalore.express.search.domain.Search;
import org.manmvou.mandalore.express.search.domain.spi.Searches;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Stub
public class InMemorySearches implements Searches {

    private final Map<UUID, Search> searches = new HashMap<>();

    public InMemorySearches(List<Search> searchList) {
        searchList.forEach(search -> searches.put(search.getId(), search));
    }

    public InMemorySearches() {
    }

    @Override
    public Search findSearchIdentifiedBy(UUID searchId) {
        return searches.get(searchId);
    }

    @Override
    public Search save(Search search) {
        searches.put(search.getId(), search);
        return search;
    }
}
