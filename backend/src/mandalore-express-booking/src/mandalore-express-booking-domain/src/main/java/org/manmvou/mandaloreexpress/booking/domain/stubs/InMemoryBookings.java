package org.manmvou.mandaloreexpress.booking.domain.stubs;



import org.manmvou.mandalore.express.annotations.ddd.Stub;
import org.manmvou.mandaloreexpress.booking.domain.Booking;
import org.manmvou.mandaloreexpress.booking.domain.spi.Bookings;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Stub
public class InMemoryBookings implements Bookings {

    private final Map<UUID, Booking> bookings = new HashMap<>();

    @Override
    public Booking findBookingById(UUID bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public Booking save(Booking booking) {
        bookings.put(booking.getId(), booking);
        return booking;
    }
}
