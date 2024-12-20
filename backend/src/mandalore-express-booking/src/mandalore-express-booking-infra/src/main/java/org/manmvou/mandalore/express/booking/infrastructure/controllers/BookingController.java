package org.manmvou.mandalore.express.booking.infrastructure.controllers;


import org.manmvou.mandaloreexpress.booking.domain.CannotBookAPartialSelection;
import org.manmvou.mandaloreexpress.booking.domain.api.PrepareBooking;
import org.manmvou.mandaloreexpress.booking.domain.spi.Bookings;
import org.manmvou.mandalore.express.booking.infrastructure.resources.Booking;
import org.manmvou.mandalore.express.booking.infrastructure.resources.Price;
import org.manmvou.mandalore.express.booking.infrastructure.resources.SpaceTrain;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.UUID;


@RestController
@RequestMapping("/bookings")
@ExposesResourceFor(Booking.class)
public class BookingController {

    private  PrepareBooking prepareBooking;
    private  Bookings bookings;
    private  EntityLinks entityLinks;

    public BookingController(PrepareBooking prepareBookins, Bookings bookings, EntityLinks entityLinks) {
        this.prepareBooking = prepareBookins;
        this.bookings = bookings;
        this.entityLinks = entityLinks;
    }


    @PostMapping
    public ResponseEntity<Booking> bookSomeSpaceTrainsFromTheSelectionOf(@RequestParam UUID searchId) {
        try {
            var domainBooking = prepareBooking.fromTheSelectionOf(searchId);
            Booking booking = toResource(domainBooking);
            return ResponseEntity.created(booking.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(booking);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find any search corresponding to id " + searchId);
        } catch (CannotBookAPartialSelection exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("/{bookingId}")
    public Booking retrieveAnExistingBooking(@PathVariable UUID bookingId) {
        var domainBooking = bookings.findBookingIdentifiedBy(bookingId);
        if (domainBooking == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return toResource(domainBooking);
    }

    private Booking toResource(org.manmvou.mandaloreexpress.booking.domain.Booking domainBooking) {
        var bookingLink = entityLinks.linkForItemResource(Booking.class, domainBooking.getId());
        return new Booking(
                domainBooking.getId(), SpaceTrain.toResource(
                    domainBooking.getSpaceTrains()),
                    Price.toResource(domainBooking.getTotalPrice()
                  )
              )
                .add(bookingLink.withSelfRel());
    }
}
