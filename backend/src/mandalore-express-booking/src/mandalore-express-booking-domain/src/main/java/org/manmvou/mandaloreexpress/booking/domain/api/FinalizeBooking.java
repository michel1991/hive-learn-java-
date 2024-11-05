package org.manmvou.mandaloreexpress.booking.domain.api;
import org.manmvou.mandaloreexpress.booking.domain.Booking;

import java.util.UUID;
public interface FinalizeBooking {
    Booking with(UUID bookingId);
}
