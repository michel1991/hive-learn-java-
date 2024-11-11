package org.manmvou.mandalore.express.search.infrastructure.resources;
import org.springframework.hateoas.RepresentationModel;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;


import org.manmvou.mandalore.express.search.domain.spaceport.Planet;
import org.manmvou.mandalore.express.search.infrastructure.controllers.SpacePortsController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import com.beyondxscratch.mandaloreexpress.search.domain.spaceport.SpacePort as DomainSpacePort
//import org.manmvou.mandalore.express.search.domain.spaceport.SpacePort;

import java.util.Set;
import java.util.stream.Collectors;

@Resource
public class SpacePort extends RepresentationModel<SpacePort> {

    private final String id;
    private final String name;
    private final Planet location;

    public SpacePort(String id, String name, Planet location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public static SpacePort toResource(org.manmvou.mandalore.express.search.domain.spaceport.SpacePort domainSpacePort) {
        SpacePort resource = new SpacePort(domainSpacePort.getId(), domainSpacePort.getName(), domainSpacePort.getLocation());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SpacePortsController.class)
                .getSpacePortIdentifiedBy(domainSpacePort.getId())).withRel(IanaLinkRelations.SELF);
        resource.add(selfLink);

        return resource;
    }

    public static SpacePorts asResourcesFor(Set<org.manmvou.mandalore.express.search.domain.spaceport.SpacePort> domainSpacePorts, String partialNameRequest) {
        Set<SpacePort> spacePorts = domainSpacePorts.stream()
                .map(SpacePort::toResource)
                .collect(Collectors.toSet());

        SpacePorts collectionModel = SpacePorts.of(spacePorts);

        if (partialNameRequest != null) {
            Link searchLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SpacePortsController.class)
                    .getSpacePorts(partialNameRequest)).withRel(IanaLinkRelations.SELF);
            Link allSpaceportsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SpacePortsController.class)
                    .getSpacePorts(null)).withRel(SPACEPORTS);

            collectionModel.add(searchLink, allSpaceportsLink);
        } else {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SpacePortsController.class)
                    .getSpacePorts(null)).withRel(IanaLinkRelations.SELF);
            collectionModel.add(selfLink);
        }

        return collectionModel;
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

    public static final String SPACEPORTS = "spaceports";

    public static class SpacePorts extends CollectionModel<SpacePort> {

        public SpacePorts(Set<SpacePort> spacePorts) {
            super(spacePorts);
        }

        public static SpacePorts of(Set<SpacePort> spacePorts) {
            return new SpacePorts(spacePorts);
        }
    }
}
