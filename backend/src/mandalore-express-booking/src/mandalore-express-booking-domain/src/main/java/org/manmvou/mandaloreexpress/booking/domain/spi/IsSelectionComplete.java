package org.manmvou.mandaloreexpress.booking.domain.spi;
import java.util.UUID;
public interface IsSelectionComplete {
    boolean of(UUID searchId);
}
