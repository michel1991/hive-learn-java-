package org.manmvou.mandalore.express.composition.root;

import org.manmvou.mandalore.express.booking.infrastructure.configurations.BookingDomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MandaloreExpressConfiguration.class, BookingDomainConfiguration.class})
//@Import(BookingDomainConfiguration.class)
public class MandaloreExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(MandaloreExpressApplication.class, args);
    }
}