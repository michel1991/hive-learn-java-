package org.manmvou.mandalore.express.search.domain.spaceport;

import java.util.Objects;

public class SpacePort {

    private final String id;
    private final String name;
    private final Planet location;

    public SpacePort(String id, String name, Planet location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Planet getLocation() {
        return location;
    }

    public boolean hasNameContaining(String partialName) {
        return name.toLowerCase().contains(partialName.toLowerCase());
    }

    public boolean isNotOnTheSamePlanetAs(SpacePort otherSpacePort) {
        return !this.location.equals(otherSpacePort.location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpacePort)) return false;
        SpacePort spacePort = (SpacePort) o;
        return id.equals(spacePort.id) && name.equals(spacePort.name) && location.equals(spacePort.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }
}

