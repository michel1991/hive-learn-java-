package org.manmvou.mandaloreexpress.booking.domain.spi;

import org.manmvou.mandaloreexpress.booking.domain.Booking;

import java.util.UUID;

public interface Bookings {
    Booking findBookingById(UUID bookingId);
    Booking save(Booking booking);
}
