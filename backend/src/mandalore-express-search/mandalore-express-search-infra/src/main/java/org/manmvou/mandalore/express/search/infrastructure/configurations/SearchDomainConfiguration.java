package org.manmvou.mandalore.express.search.infrastructure.configurations;

import org.manmvou.mandalore.express.annotations.ddd.AntiCorruptionLayer;
import org.manmvou.mandalore.express.annotations.ddd.DomainService;
import org.manmvou.mandalore.express.annotations.ddd.Stub;


import org.manmvou.mandalore.express.search.domain.Search;
//import com.beyondxscratch.mandaloreexpress.search.domain.Search;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType.ANNOTATION;
import org.springframework.context.annotation.FilterType;




@Configuration
@ComponentScan(
        //basePackageClasses = Booking.class,
        basePackages = {
                //"org.manmvou.mandaloreexpress.booking.domain",
                "org.manmvou.mandalore.express.search.domain"
                //"org.manmvou.mandalore.express.booking.infrastructure.clients"
        },
        //includeFilters = @Filter(type = FilterType.ANNOTATION, value = {DomainService.class, Stub.class, AntiCorruptionLayer.class})
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {DomainService.class, Stub.class, AntiCorruptionLayer.class}
        )
)
public class SearchDomainConfiguration {
}
