package org.manmvou.mandaloreexpress.booking.domain;



import org.manmvou.mandalore.express.annotations.ddd.DomainService;
import org.manmvou.mandaloreexpress.booking.domain.api.FinalizeBooking;
import org.manmvou.mandaloreexpress.booking.domain.api.PrepareBooking;
import org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain;
import org.manmvou.mandaloreexpress.booking.domain.spi.Bookings;
import org.manmvou.mandaloreexpress.booking.domain.spi.GetTrainsToBook;
import org.manmvou.mandaloreexpress.booking.domain.spi.IsSelectionComplete;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@DomainService
public class SpaceTrainsBooker implements PrepareBooking, FinalizeBooking {

    private final IsSelectionComplete isSelectionComplete;
    private final GetTrainsToBook getTrainsToBook;
    private final Bookings bookings;

    public SpaceTrainsBooker(IsSelectionComplete isSelectionComplete, GetTrainsToBook getTrainsToBook, Bookings bookings) {
        this.isSelectionComplete = isSelectionComplete;
        this.getTrainsToBook = getTrainsToBook;
        this.bookings = bookings;
    }

    @Override
    public IsSelectionComplete getIsSelectionComplete() {
        return this.isSelectionComplete;
    }

    @Override
    public GetTrainsToBook getGetTrainsToBook() {
        return this.getTrainsToBook;
    }

    @Override
    public Bookings getBookings() {
        return this.bookings;
    }

    @Override
    public Booking fromTheSelectionOf(UUID searchId) {
        if (!isSelectionComplete.of(searchId)) {
            throw new CannotBookAPartialSelection();
        }

        List<SpaceTrain> spaceTrainsToBook = getTrainsToBook.from(searchId);
        return bookings.save(new Booking(spaceTrainsToBook));
    }

    @Override
    public Booking with(UUID bookingId) {
        Booking booking = retrieveBooking(bookingId);

        if (booking.isFinalized()) {
            throw new IllegalStateException("Booking " + bookingId + " is already finalized");
        }

        return bookings.save(booking.finalizeBooking());
    }

    private Booking retrieveBooking(UUID bookingId) {
        Booking booking =  bookings.findBookingById(bookingId);
        if(booking != null){
            return booking;
        }
        throw new NoSuchElementException("Unknown booking " + bookingId);
        /*return bookings.findBookingById(bookingId)
                .orElseThrow(() -> new NoSuchElementException("Unknown booking " + bookingId));*/
    }
}
