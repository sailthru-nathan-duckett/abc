package com.nduckett.abc.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RootResource {

    @GET
    public Response sayHello() throws InterruptedException {
        long sleepDuration = (long) (Math.random() * 200);
        Thread.sleep(sleepDuration);
        return Response
                .ok(Map.of("message", "Hello World", "sleepDuration", sleepDuration))
                .build();
    }
}
