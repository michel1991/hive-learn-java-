package org.manmvou.mandalore.express.search.infrastructure.controllers;
// org.manmvou.mandalore.express.search.domain.spacetrain
import org.manmvou.mandalore.express.search.domain.api.RetrieveSpacePorts;
//import com.beyondxscratch.mandaloreexpress.search.domain.api.RetrieveSpacePorts;
import org.manmvou.mandalore.express.search.infrastructure.resources.SpacePort;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.SpacePorts;
import org.manmvou.mandalore.express.search.infrastructure.resources.SpacePort.SpacePorts;
//import com.beyondxscratch.mandaloreexpress.search.infrastructure.resources.ResourceAssembler;
import static org.manmvou.mandalore.express.search.infrastructure.resources.SpacePort.asResourcesFor;
//import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

//import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/spaceports")
public class SpacePortsController {

    private final RetrieveSpacePorts retrieveSpacePorts;

    public SpacePortsController(RetrieveSpacePorts retrieveSpacePorts) {
        this.retrieveSpacePorts = retrieveSpacePorts;
    }

    private static final String DEFAULT_NAME = "";

    @GetMapping
    public ResponseEntity<SpacePorts> getSpacePorts(
            @RequestParam(name = "withNameContaining", required = false) String partialName) {

        if (partialName == null) {
            partialName = DEFAULT_NAME;
        }

        SpacePorts spacePorts = asResourcesFor(retrieveSpacePorts.havingInTheirName(partialName), partialName);

        return ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES))
                .body(spacePorts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpacePort> getSpacePortIdentifiedBy(@PathVariable String id) {
        SpacePort spacePort = SpacePort.toResource(retrieveSpacePorts.identifiedBy(id));
        // spacePort = retrieveSpacePorts.identifiedBy(id).toResource();

        return ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES))
                .body(spacePort);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoSuchElementException(HttpServletResponse response, NoSuchElementException exception) throws IOException {
        response.sendError(SC_NOT_FOUND, exception.getMessage());
    }


}