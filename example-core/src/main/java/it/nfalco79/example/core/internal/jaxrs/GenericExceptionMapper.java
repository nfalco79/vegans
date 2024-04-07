package it.nfalco79.example.core.internal.jaxrs;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper extends AbstractExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception e) {
        return Response.status(Status.INTERNAL_SERVER_ERROR) //
                .entity(buildErrorMessage(e)) //
                .build();
    }

}
