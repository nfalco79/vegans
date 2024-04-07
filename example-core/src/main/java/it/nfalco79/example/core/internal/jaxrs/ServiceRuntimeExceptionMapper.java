package it.nfalco79.example.core.internal.jaxrs;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import it.nfalco79.example.api.core.ExampleServiceRuntimeException;

@Provider
public class ServiceRuntimeExceptionMapper extends AbstractExceptionMapper implements ExceptionMapper<ExampleServiceRuntimeException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ExampleServiceRuntimeException e) {
        return Response.status(Status.BAD_REQUEST) //
                .entity(buildErrorMessage(e.getErrorCode(), e)) //
                .build();
    }

}
