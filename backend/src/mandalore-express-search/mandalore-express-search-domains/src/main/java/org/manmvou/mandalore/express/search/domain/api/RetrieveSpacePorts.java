package org.manmvou.mandalore.express.search.domain.api;

import org.manmvou.mandalore.express.search.domain.spaceport.SpacePort;
import org.manmvou.mandalore.express.search.domain.spi.SpacePorts;

import java.util.Set;

public interface RetrieveSpacePorts {
    SpacePorts getSpacePorts();

    Set<SpacePort> havingInTheirName(String partialName);

    SpacePort identifiedBy(String id);
}
