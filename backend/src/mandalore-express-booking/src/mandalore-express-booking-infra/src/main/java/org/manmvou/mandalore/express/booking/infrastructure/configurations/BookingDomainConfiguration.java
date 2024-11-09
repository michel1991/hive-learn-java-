package org.manmvou.mandalore.express.booking.infrastructure.configurations;

import org.manmvou.mandalore.express.annotations.ddd.AntiCorruptionLayer;
import org.manmvou.mandalore.express.annotations.ddd.DomainService;
import org.manmvou.mandalore.express.annotations.ddd.Stub;
import org.manmvou.mandaloreexpress.booking.domain.Booking;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
// includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class)
// org.manmvou.mandaloreexpress.booking.domain

/**
  @Configuration
     @ComponentScan(
          basePackages = "com.arhohuttunen.coffeeshop.application",
          includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class)
  )
 */
@Configuration
@ComponentScan(
        //basePackageClasses = Booking.class,
        basePackages = {
                "org.manmvou.mandaloreexpress.booking.domain",
                "org.manmvou.mandalore.express.booking.infrastructure.clients"
        },
        //includeFilters = @Filter(type = FilterType.ANNOTATION, value = {DomainService.class, Stub.class, AntiCorruptionLayer.class})
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {DomainService.class, Stub.class, AntiCorruptionLayer.class}
        )
)
public class BookingDomainConfiguration {
}
