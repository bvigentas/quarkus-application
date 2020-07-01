package com.github.bvigentas.rickandmorty;


import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Traced
@Path("/api")
@RegisterRestClient
public interface RickAndMortyService {

    @Timed
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/character/{character}")
    @Retry(maxRetries = 5)
    /*@Fallback(fallbackMethod = "characterFallback")*/
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 10000, successThreshold = 2)
    Character getCharacter(@PathParam("character") String character);

    //void characterFallback();

}
