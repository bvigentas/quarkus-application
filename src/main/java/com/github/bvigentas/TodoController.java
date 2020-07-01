package com.github.bvigentas;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)

public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    @Inject
    TodoService todoService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Todo todo) {
        todoService.add(todo);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "fallBackList")
    public List<Todo> list() {
        maybeFail(String.format("Invocation failed"));

        LOGGER.info("Invocation successfully");
        return todoService.list();
    }

    public List<Todo> fallBackList() {
        LOGGER.info("Caiu no fallback");
        return new ArrayList<Todo>();
    }


    private void maybeFail(String failureLogMessage) {
        if (new Random().nextBoolean()) {
            LOGGER.error(failureLogMessage);
            throw new RuntimeException("Resource failure.");
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Todo todo) {
       todoService.update(id, todo);
       return Response.ok().build();
    }

}
