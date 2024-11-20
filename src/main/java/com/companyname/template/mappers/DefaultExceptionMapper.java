package com.companyname.template.mappers;

import io.sentry.Sentry;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error("Uncaught exception: {}", e.getMessage());
        // Report to Sentry
        Sentry.captureException(e);
        return Response.serverError()
                .entity(Map.of("message", "Internal Server Error"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
