package it.nfalco79.example.core.internal.rest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.lang.NonNull;

import it.nfalco79.example.api.dto.PolygonDTO;
import it.nfalco79.example.core.service.PolygonService;

@Path("/polygons")
public class PolygonResource {
    private final PolygonService service;

    @Inject
    public PolygonResource(PolygonService service) {
        this.service = service;
    }

    @POST
    @Path("/area")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public double area(@Valid @NotNull @NonNull PolygonDTO polygon) {
        return service.area(polygon);
    }

    @POST
    @Path("/perimeter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public double perimeter(@Valid @NotNull @NonNull PolygonDTO polygon) {
        return service.perimeter(polygon);
    }
}
