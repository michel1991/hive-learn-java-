package org.manmvou.mandalore.express.composition.root;

import org.manmvou.mandalore.express.annotations.ddd.AntiCorruptionLayer;
import org.manmvou.mandalore.express.annotations.ddd.DomainService;
import org.manmvou.mandalore.express.annotations.ddd.Stub;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        includeFilters = @Filter(
                type = FilterType.ANNOTATION,
                classes = {DomainService.class, Stub.class, AntiCorruptionLayer.class}
        )
)
public class MandaloreExpressConfiguration {
}