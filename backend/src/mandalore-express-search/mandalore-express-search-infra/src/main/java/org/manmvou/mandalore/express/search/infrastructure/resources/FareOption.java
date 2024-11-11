package org.manmvou.mandalore.express.search.infrastructure.resources;

//import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.ComfortClass;
import org.manmvou.mandalore.express.search.domain.spacetrain.fare.ComfortClass;
//import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.FareOption as DomainFareOption;
//import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.FareOptions as DomainFareOptions;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.LinkBuilder;
import java.util.UUID;
import java.util.Set;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;
import java.util.stream.Collectors;

@Resource
public class FareOption extends RepresentationModel<FareOption> {

    private final UUID id;
    private final ComfortClass comfortClass;
    private final Price price;

    public FareOption(UUID id, ComfortClass comfortClass, Price price) {
        this.id = id;
        this.comfortClass = comfortClass;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public ComfortClass getComfortClass() {
        return comfortClass;
    }

    public Price getPrice() {
        return price;
    }

    // Extension for DomainFareOption
    public static FareOption toResource(org.manmvou.mandalore.express.search.domain.spacetrain.fare.FareOption domainFareOption, LinkBuilder spaceTrainLink) {
        var priceResource = Price.toResource(domainFareOption.getPrice());
        FareOption fareOption = new FareOption(
                domainFareOption.getId(),
                domainFareOption.getComfortClass(),
                priceResource
        );

        if (spaceTrainLink != null) {
            fareOption.add(
                    spaceTrainLink.slash("fareoptions")
                            .slash(domainFareOption.getId().toString())
                            .slash("select")
                            .withRel("select")
            );
        }
        return fareOption;
    }

    public static class FareOptions{
        Set<FareOption> fareOptions;

        public FareOptions(Set<FareOption> fareOptions) {
            this.fareOptions = fareOptions;
        }

        public static Set<FareOption> toResource(
                org.manmvou.mandalore.express.search.domain.spacetrain.fare.FareOption.FareOptions domainFareOptions,
                LinkBuilder spaceTrainLink) {
            return domainFareOptions.getOptions().stream()
                    //.map(fareOption -> fareOption.toResource(spaceTrainLink))
                    .map(fareOption -> FareOption.toResource(fareOption, null))
                    .collect(Collectors.toSet());
        }
    }
}

// Type alias for Set<FareOption>
/*public static Set<FareOption> toResource(DomainFareOptions domainFareOptions, LinkBuilder spaceTrainLink) {
    return domainFareOptions.stream()
            .map(fareOption -> fareOption.toResource(spaceTrainLink))
            .collect(Collectors.toSet());
}*/



