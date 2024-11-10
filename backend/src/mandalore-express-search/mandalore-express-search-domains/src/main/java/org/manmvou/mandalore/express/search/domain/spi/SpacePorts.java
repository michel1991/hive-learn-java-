package org.manmvou.mandalore.express.search.domain.spi;

import org.manmvou.mandalore.express.search.domain.spaceport.SpacePort;

import java.util.Set;

@FunctionalInterface
public interface SpacePorts {
    Set<SpacePort> getAllSpacePorts();
}
