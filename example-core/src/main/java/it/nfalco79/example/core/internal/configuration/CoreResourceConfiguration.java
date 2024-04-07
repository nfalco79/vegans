package it.nfalco79.example.core.internal.configuration;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import it.nfalco79.example.core.internal.jaxrs.WebApplicationExceptionMapper;
import it.nfalco79.example.core.internal.rest.PolygonResource;

@ApplicationPath("api")
public class CoreResourceConfiguration extends ResourceConfig {

    public CoreResourceConfiguration() {
        packages( //
                PolygonResource.class.getPackageName(), //
                WebApplicationExceptionMapper.class.getPackageName());
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }

}
