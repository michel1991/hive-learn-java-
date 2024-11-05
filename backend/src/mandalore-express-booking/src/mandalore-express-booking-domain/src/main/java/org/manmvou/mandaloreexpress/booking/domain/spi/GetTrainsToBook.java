package org.manmvou.mandaloreexpress.booking.domain.spi;


import org.manmvou.mandaloreexpress.booking.domain.spacetrain.SpaceTrain;

import java.util.List;
import java.util.UUID;

public interface GetTrainsToBook {
    List<SpaceTrain> from(UUID searchId);
}
