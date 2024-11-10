package org.manmvou.mandalore.express.search.domain.stubs;



import  org.manmvou.mandalore.express.search.domain.spaceport.SpacePort;
import org.manmvou.mandalore.express.search.domain.spi.SpacePorts;
import org.manmvou.mandalore.express.annotations.ddd.Stub;
import org.manmvou.mandalore.express.search.domain.spaceport.Planet;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stub
public class InMemorySpacePorts implements SpacePorts {

    private final Set<SpacePort> spacePorts = Stream.of(
            new SpacePort(toUUIDString("Uscru"), "Uscru District", Planet.CORUSCANT),
            new SpacePort(toUUIDString("CoCo"), "CoCo Town", Planet.CORUSCANT),
            new SpacePort(toUUIDString("Nevarro"), "Nevarro City", Planet.NEVARRO),
            new SpacePort(toUUIDString("Mos Eisley"), "Mos Eisley", Planet.TATOOINE),
            new SpacePort(toUUIDString("Mos Espa"), "Mos Espa", Planet.TATOOINE),
            new SpacePort(toUUIDString("Keldabe"), "Keldabe", Planet.MANDALORE),
            new SpacePort(toUUIDString("Sundari"), "Sundari", Planet.MANDALORE)
    ).collect(Collectors.toSet());

    @Override
    public Set<SpacePort> getAllSpacePorts() {
        return Set.copyOf(spacePorts);
    }

    private static String toUUIDString(String name) {
        return UUID.nameUUIDFromBytes(name.getBytes()).toString();
    }
}