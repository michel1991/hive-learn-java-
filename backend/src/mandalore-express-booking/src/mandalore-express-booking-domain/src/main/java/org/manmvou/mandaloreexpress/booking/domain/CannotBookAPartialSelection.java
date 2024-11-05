package org.manmvou.mandaloreexpress.booking.domain;

public class CannotBookAPartialSelection extends RuntimeException {
    public CannotBookAPartialSelection() {
        super("cannot book a partial selection");
    }
}