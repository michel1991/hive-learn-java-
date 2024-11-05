package org.manmvou.mandaloreexpress.money.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // or ElementType.METHOD, depending on your use case
@Retention(RetentionPolicy.RUNTIME)
public @interface OneRepCredit {
}
