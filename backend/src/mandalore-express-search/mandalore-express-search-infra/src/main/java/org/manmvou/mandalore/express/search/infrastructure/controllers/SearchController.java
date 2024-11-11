package org.manmvou.mandalore.express.search.infrastructure.controllers;

import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.CrossDomainHypermediaResolver;
import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.Relation.CreateBooking;
//import com.beyondxscratch.mandaloreexpress.search.domain.api.*;
//import org.manmvou.mandalore.express.search.domain.criteria.Criteria;
import org.manmvou.mandalore.express.hypermedia.infrastructure.Resource;


//import com.beyondxscratch.mandaloreexpress.infrastructure.hypermedia.CrossDomainHypermediaResolver;
import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.CrossDomainHypermediaResolver;
//import com.beyondxscratch.mandaloreexpress.infrastructure.hypermedia.Relation.CreateBooking;
import org.manmvou.mandalore.express.hypermedia.infrastructure.hypermedia.Relation.CreateBooking;
import org.manmvou.mandalore.express.search.domain.api.*;
//import com.beyondxscratch.mandaloreexpress.search.domain.api.*;
//import org.manmvou.mandalore.express.search.domain.criteria.Criteria
// import com.beyondxscratch.mandaloreexpress.search.domain.criteria.Criteria as DomainCriteria;
//import com.beyondxscratch.mandaloreexpress.search.domain.spaceport.SpacePort;
import org.manmvou.mandalore.express.search.domain.spaceport.SpacePort;
//import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.Bound;
import org.manmvou.mandalore.express.search.domain.criteria.Journey;
import org.manmvou.mandalore.express.search.domain.spacetrain.Bound;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.FareOption;
import org.manmvou.mandalore.express.search.infrastructure.resources.*;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.Search;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.SelectedSpaceTrain;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.Selection;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.SpaceTrain;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.SpaceTrains;
import org.manmvou.mandalore.express.search.infrastructure.resources.SpaceTrain.SpaceTrains;
// import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.toResource;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.Criteria;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.FareOptions
import org.manmvou.mandalore.express.search.infrastructure.resources.FareOption.FareOptions;

/*import com.beyondxscratch.mandaloreexpress.search.domain.Search as DomainSearch
import com.beyondxscratch.mandaloreexpress.search.domain.criteria.Criteria as DomainCriteria
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.SpaceTrain as DomainSpaceTrain
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.SpaceTrains as DomainSpaceTrains
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.FareOption as DomainFareOption
import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.fare.FareOptions as DomainFareOptions*/

//import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain.SpaceTrains;




//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.*;
//import org.manmvou.mandalore.express.search.infrastructure.resources.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//import com.beyondxscratch.mandaloreexpress.search.domain.Search;
//import org.manmvou.mandalore.express.search.domain.Search;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/searches")
@ExposesResourceFor(Search.class)
public class SearchController {

    private final SearchForSpaceTrains searchForSpaceTrains;
    private final RetrieveSpacePorts retrieveSpacePorts;
    private final SelectSpaceTrain selectSpaceTrain;
    private final GetSearch getSearch;
    private final CrossDomainHypermediaResolver crossDomainHypermediaResolver;
    private final EntityLinks entityLinks;

    public SearchController(SearchForSpaceTrains searchForSpaceTrains,
                            RetrieveSpacePorts retrieveSpacePorts,
                            SelectSpaceTrain selectSpaceTrain,
                            GetSearch getSearch,
                            CrossDomainHypermediaResolver crossDomainHypermediaResolver,
                            EntityLinks entityLinks) {
        this.searchForSpaceTrains = searchForSpaceTrains;
        this.retrieveSpacePorts = retrieveSpacePorts;
        this.selectSpaceTrain = selectSpaceTrain;
        this.getSearch = getSearch;
        this.crossDomainHypermediaResolver = crossDomainHypermediaResolver;
        this.entityLinks = entityLinks;
    }

    @PostMapping
    public ResponseEntity<Search> performASearch(@RequestBody org.manmvou.mandalore.express.search.infrastructure.resources.Criteria criteria) {
        try {
           // DomainCriteria domainCriteria = criteria.toDomainObject();
            //org.manmvou.mandalore.express.search.domain.criteria.Criteria domainCriteria = criteria.toDomainObject();
            org.manmvou.mandalore.express.search.domain.criteria.Criteria domainCriteria = toDomainObject(criteria);
            //DomainSearch domainSearch = searchForSpaceTrains.satisfying(domainCriteria);
            org.manmvou.mandalore.express.search.domain.Search domainSearch = searchForSpaceTrains.satisfying(domainCriteria);
            //Search search = domainSearch.toResource();
            Search search = this.toResource(domainSearch);
            return ResponseEntity.created(search.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(search);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    private Search toResource(org.manmvou.mandalore.express.search.domain.Search domainSearch) {
        LinkBuilder searchLink = searchLink(domainSearch.getId());
        var criteriaSearchDomain = domainSearch.getCriteria();
        var domainJourney = criteriaSearchDomain.getJourneys();

        var journey = org.manmvou.mandalore.express.search.infrastructure.resources.Journey.Journeys.toResource(
                domainJourney
        );
        var resourceSearchCriteria = new Criteria(journey);

        Search searchResource = new Search(
                //domainSearch.getId(), toResource(domainSearch.getCriteria())
                domainSearch.getId(), resourceSearchCriteria
        )
        .add(searchLink.withSelfRel())
        .add(searchLink.slash("selection").withRel("selection"));

        if (domainSearch.isSelectionComplete()) {
            searchResource.add(crossDomainHypermediaResolver.resolveLinkFor(
                    new CreateBooking(domainSearch.getId()))
            );
        }
        //org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain::getBound;
        domainSearch.getSpaceTrains().getTrains().stream()
                .map(org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain::getBound)
                .distinct()
                .forEach(bound -> {
                    linkToSpaceTrainsForBound(
                            searchResource,
                            domainSearch.getId(),
                            bound,
                            LinkRelation.of("all-" + bound.toString().toLowerCase(Locale.getDefault()) + "s"),
                            false
                    );


                    if (domainSearch.getSelection().hasASelectionFor(bound.oppositeWay())) {
                        linkToSpaceTrainsForBound(
                                searchResource,
                                domainSearch.getId(),
                                bound,
                                LinkRelation.of(bound.name().toLowerCase(Locale.getDefault()) + "s-for-current-selection"),
                                true
                        );
                    }


                });
        return searchResource;
    }

    @GetMapping("/{searchId}")
    public ResponseEntity<Search> retrieveAnExistingSearch(@PathVariable UUID searchId) {
        //DomainSearch domainSearch = retrieveSearch(searchId);
        org.manmvou.mandalore.express.search.domain.Search domainSearch = retrieveSearch(searchId);
        //return ResponseEntity.ok(domainSearch.toResource());
        return ResponseEntity.ok(this.toResource(domainSearch));
    }



    @GetMapping("/{searchId}/spacetrains")
    public ResponseEntity<SpaceTrains> retrieveSpaceTrainsForBound(@PathVariable UUID searchId,
                                                                   @RequestParam Bound bound,
                                                                   @RequestParam(defaultValue = "false") boolean onlySelectable) {
        /*org.manmvou.mandalore.express.search.domain.Search domainSearch = retrieveSearch(searchId);
        LinkBuilder searchLink = searchLink(searchId);
       // DomainSpaceTrains spaceTrains = onlySelectable ? domainSearch.selectableSpaceTrains(bound) : domainSearch.spaceTrains(bound);
        org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain.SpaceTrains spaceTrains = onlySelectable ? domainSearch.selectableSpaceTrains(bound) : domainSearch.spaceTrains(bound);
        //SpaceTrains resourceSpaceTrains = new SpaceTrains(spaceTrains.toResource(searchLink, !onlySelectable));
        SpaceTrains resourceSpaceTrains = new SpaceTrains(spaceTrains.toResource(searchLink, !onlySelectable));

        resourceSpaceTrains.add(searchLink.withRel("search"))
                .add(searchLink.slash("selection").withRel("selection"))
                .linkToSpaceTrainsForBound(
                        searchId,
                        bound,
                        LinkRelation.of(IanaLinkRelations.SELF.value()),
                        onlySelectable
                );
        return ResponseEntity.ok(resourceSpaceTrains);*/
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{searchId}/spacetrains/{spaceTrainNumber}/fareoptions/{fareId}/select")
    public ResponseEntity<Selection> selectSpaceTrainWithFare(@PathVariable UUID searchId,
                                                              @PathVariable String spaceTrainNumber,
                                                              @PathVariable UUID fareId,
                                                              @RequestParam(defaultValue = "false") boolean resetSelection) {
        /*org.manmvou.mandalore.express.search.domain.Search domainSearch =
                selectSpaceTrain.havingNumberWithFareInSearchByResettingSelection(
                        spaceTrainNumber,
                        fareId,
                        searchId,
                        resetSelection
                );
        //Selection selection = domainSearch.toSelectionResource();
        Selection selection = this.toSelectionResource(domainSearch);
        return ResponseEntity.ok(selection);*/
        return ResponseEntity.ok(null);
    }

    //import com.beyondxscratch.mandaloreexpress.search.domain.spacetrain.SpaceTrain
    private Selection toSelectionResource(org.manmvou.mandalore.express.search.domain.Search domainSearch) {
        LinkBuilder searchLink = searchLink(domainSearch.getId());
        List<SelectedSpaceTrain> selectedSpaceTrains = domainSearch.getSelection().getSpaceTrains().stream()
                .map(selectedSpaceTrain -> {
                   // DomainSpaceTrain spaceTrain = domainSearch.getSpaceTrains().stream()

                    org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain spaceTrain = domainSearch.getSpaceTrains().getTrains().stream()
                            .filter(train -> train.getNumber().equals(selectedSpaceTrain.getSpaceTrainNumber()))
                            .findFirst()
                            .orElseThrow();

                    org.manmvou.mandalore.express.search.domain.spacetrain.fare.FareOption fareOptionSearchDomain =
                            spaceTrain.getFareOptions().getOptions().stream()
                            .filter(option -> option.getId().equals(selectedSpaceTrain.getFareId()))
                            .findFirst()
                            .orElseThrow();

                    FareOption.toResource(fareOptionSearchDomain, null);

                    return new SelectedSpaceTrain(
                            spaceTrain.getNumber(),
                            spaceTrain.getBound(),
                            spaceTrain.getOriginId(),
                            spaceTrain.getDestinationId(),
                            spaceTrain.getSchedule().getDeparture(),
                            spaceTrain.getSchedule().getArrival(),
                            FareOption.toResource(fareOptionSearchDomain, null)
                    );
                })
                .sorted(Comparator.comparingInt(selectedSpaceTrain -> selectedSpaceTrain.getBound().ordinal()))
                .collect(Collectors.toList());


        Price priceResource  = domainSearch.getSelection().getTotalPrice()
                != null ? Price.toResource(domainSearch.getSelection().getTotalPrice()): null;


        Selection selection = new Selection(selectedSpaceTrains, priceResource)
                .add(searchLink.withRel("search"))
                .add(searchLink.slash("selection").withSelfRel())
                .add(searchLink.slash("selection").withRel("selection"));
        if (domainSearch.isSelectionComplete()) {
            selection.add(crossDomainHypermediaResolver.resolveLinkFor(new CreateBooking(domainSearch.getId())));
        }
        domainSearch.getSpaceTrains().getTrains().stream()
                .map(org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain::getBound)
                .distinct()
                .forEach(bound -> {
                    linkToSpaceTrainsForBound(
                            selection,
                            domainSearch.getId(),
                            bound, LinkRelation.of("all-" + bound.name().toLowerCase(Locale.getDefault()) + "s"),
                            false
                    );
                    if (domainSearch.getSelection().hasASelectionFor(bound.oppositeWay())) {

                        linkToSpaceTrainsForBound(
                                selection,
                                domainSearch.getId(),
                                bound,
                                LinkRelation.of(bound.name().toLowerCase(Locale.getDefault()) + "s-for-current-selection"),
                                true
                        );
                    }
                });
        return selection;
    }



    @GetMapping("/{searchId}/selection")
    public ResponseEntity<Selection> retrieveSelection(@PathVariable UUID searchId) {
        org.manmvou.mandalore.express.search.domain.Search domainSearch = retrieveSearch(searchId);
        //Selection selection = domainSearch.toSelectionResource();
        Selection selection = toSelectionResource(domainSearch);
        return ResponseEntity.ok(selection);
    }



    private <R extends RepresentationModel<R>> R linkToSpaceTrainsForBound(
            R resource,
            UUID searchId,
            Bound bound,
            LinkRelation linkRelation,
            boolean onlySelectable) {
        return resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SearchController.class)
                .retrieveSpaceTrainsForBound(searchId, bound, onlySelectable)).withRel(linkRelation));
    }

    private org.manmvou.mandalore.express.search.domain.Search retrieveSearch(UUID searchId) {
        try {
            return getSearch.identifiedBy(searchId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unknown search id " + searchId);
        }
    }


    private LinkBuilder searchLink(UUID searchId) {
        return entityLinks.linkForItemResource(Search.class, searchId);
    }

    //private DomainCriteria toDomainObject(Criteria criteria) {
    // com.beyondxscratch.mandaloreexpress.search.domain.criteria.Criteria
    private static org.manmvou.mandalore.express.search.domain.criteria.Criteria toDomainObject(Criteria criteria) {
        List<Journey> listJourney = criteria.getJourneys().stream()
                .map(journey -> new Journey(
                        journey.getDepartureSpacePortId().toString(),
                        LocalDateTime.parse(journey.getDepartureSchedule()),
                        journey.getArrivalSpacePortId().toString()))
                .collect(Collectors.toList());
        var result = Journey.Journeys.of(listJourney);
        return new org.manmvou.mandalore.express.search.domain.criteria.Criteria(result);

    }

}
