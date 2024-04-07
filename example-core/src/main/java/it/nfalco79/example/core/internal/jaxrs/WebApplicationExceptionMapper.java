package it.nfalco79.example.core.internal.jaxrs;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper extends AbstractExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(WebApplicationException e) {
        return Response.status(e.getResponse().getStatus()) //
                .entity(buildErrorMessage(e)) //
                .build();
    }
}