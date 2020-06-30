package com.github.bvigentas;

import org.eclipse.microprofile.faulttolerance.Retry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)

public class TodoController {

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
    @Retry(maxRetries = 4)
    public List<Todo> list() {
        return todoService.list();
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Todo todo) {
       todoService.update(id, todo);
       return Response.ok().build();
    }

}
