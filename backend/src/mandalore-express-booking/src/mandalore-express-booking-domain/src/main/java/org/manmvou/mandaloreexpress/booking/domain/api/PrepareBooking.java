package org.manmvou.mandaloreexpress.booking.domain.api;


import org.manmvou.mandaloreexpress.booking.domain.Booking;
import org.manmvou.mandaloreexpress.booking.domain.spi.Bookings;
import org.manmvou.mandaloreexpress.booking.domain.spi.GetTrainsToBook;
import org.manmvou.mandaloreexpress.booking.domain.spi.IsSelectionComplete;

import java.util.UUID;

public interface PrepareBooking {
    IsSelectionComplete getIsSelectionComplete();
    GetTrainsToBook getGetTrainsToBook();
    Bookings getBookings();

    Booking fromTheSelectionOf(UUID searchId);
}
