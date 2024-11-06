package org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia;

import java.util.UUID;

public abstract class Relation {

    public static class CreateBooking extends Relation {

        private final UUID searchId;

        public CreateBooking(UUID searchId) {
            this.searchId = searchId;
        }

        public UUID getSearchId() {
            return searchId;
        }

        @Override
        public String toString() {
            return "CreateBooking{" +
                    "searchId=" + searchId +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CreateBooking that = (CreateBooking) o;
            return searchId.equals(that.searchId);
        }

        @Override
        public int hashCode() {
            return searchId.hashCode();
        }
    }
}
