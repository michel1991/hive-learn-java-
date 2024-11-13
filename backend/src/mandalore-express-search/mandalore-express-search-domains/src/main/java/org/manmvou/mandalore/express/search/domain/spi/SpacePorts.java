package org.manmvou.mandalore.express.search.domain.spi;

import org.manmvou.mandalore.express.search.domain.spaceport.SpacePort;

import java.util.NoSuchElementException;
import java.util.Set;

@FunctionalInterface
public interface SpacePorts {
    Set<SpacePort> getAllSpacePorts();

    private SpacePort findBySpacePortId(String spacePortId) {
        String id = spacePortId.contains("/") ?
                spacePortId.substring(spacePortId.lastIndexOf("/") + 1) :
                spacePortId;

        return getAllSpacePorts().stream()
                .filter(port -> port.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("SpacePort not found: " + id));
    }

    default SpacePort find(String spacePortId){
        return findBySpacePortId(spacePortId);
    }
}
