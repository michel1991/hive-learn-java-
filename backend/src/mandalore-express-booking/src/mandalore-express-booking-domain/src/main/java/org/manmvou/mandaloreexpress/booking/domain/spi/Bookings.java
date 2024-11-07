package org.manmvou.mandaloreexpress.booking.domain.spi;

import org.manmvou.mandaloreexpress.booking.domain.Booking;

import java.util.UUID;

public interface Bookings {
    Booking findBookingIdentifiedBy(UUID bookingId);
    Booking save(Booking booking);
}
