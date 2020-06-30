package com.github.bvigentas;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.PanacheQuery;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    public Todo add(Todo todo) {
        todoRepository.persist(todo);
        return todo;
    }

    public List<Todo> list() {
        final List<Todo> todos = todoRepository.listAll();
        return todos;
    }

    public Todo update(String id, Todo todo) {

        final Todo todoDB = todoRepository.findById(new ObjectId(id));

        todoDB.setDescription(todo.getDescription());
        todoDB.setDone(todo.isDone());
        todoDB.setCreationDate(todo.getCreationDate());

        todoRepository.update(todoDB);

        return todoDB;

    }

}
