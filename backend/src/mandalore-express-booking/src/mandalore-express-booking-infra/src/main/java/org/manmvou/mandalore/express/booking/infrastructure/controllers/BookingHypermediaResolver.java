package org.manmvou.mandalore.express.booking.infrastructure.controllers;

import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.HypermediaResolver;
import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.Relation;
import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.Relation.CreateBooking;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookingHypermediaResolver implements HypermediaResolver {

    @Override
    public boolean accept(Relation relation) {
        System.out.println("hello accept");
        //return relation instanceof CreateBooking;
        return true;
    }

    @Override
    public Link linkFor(Relation relation) {
        System.out.println("for link");
        /*if (relation instanceof CreateBooking) {
            CreateBooking createBooking = (CreateBooking) relation;
            return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class)
                            .bookSomeSpaceTrainsFromTheSelectionOf(createBooking.getSearchId()))
                    .withRel("create-booking");
        }*/
        throw new IllegalArgumentException("Unsupported relation type: " + relation.getClass());
    }
}
