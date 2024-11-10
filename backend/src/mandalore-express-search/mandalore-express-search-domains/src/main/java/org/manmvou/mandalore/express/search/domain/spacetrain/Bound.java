package org.manmvou.mandalore.express.search.domain.spacetrain;

public enum Bound {
    OUTBOUND,
    INBOUND;

    public static Bound fromJourneyIndex(int journeyIndex) {
        Bound[] bounds = values();
        int maxIndex = bounds.length;

        if (journeyIndex < 0 || journeyIndex >= maxIndex) {
            throw new IllegalArgumentException(
                    "Journey index " + journeyIndex + " is outside the supported range [0," + (maxIndex - 1) + "]"
            );
        }
        return bounds[journeyIndex];
    }

    public Bound oppositeWay() {
        return fromJourneyIndex((this.ordinal() + 1) % 2);
    }
}
