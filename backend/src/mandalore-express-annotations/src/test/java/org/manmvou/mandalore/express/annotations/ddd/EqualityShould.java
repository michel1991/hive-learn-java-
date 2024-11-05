package org.manmvou.mandalore.express.annotations.ddd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public interface EqualityShould<T> {

    /*@Test
    default void beEqualToTheSameValue(T value, T otherValue) {
        Assertions.assertThat(value).isEqualTo(otherValue);
        Assertions.assertThat(value).hasSameHashCodeAs(otherValue);
    }

    @Test
    default void notBeEqualToDifferentValue(T value, T otherValue) {
        Assertions.assertThat(value).isNotEqualTo(otherValue);
        Assertions.assertThat(value.hashCode()).isNotEqualTo(otherValue.hashCode());
    }*/
}
