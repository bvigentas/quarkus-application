package com.github.bvigentas.rickandmorty;


import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@RegisterRestClient
public interface RickAndMortyService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/character/{character}")
    @Retry(maxRetries = 5)
    @CircuitBreaker(requestVolumeThreshold = 5)
    Character getCharacter(@PathParam("character") String character);

}
