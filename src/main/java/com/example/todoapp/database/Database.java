package com.example.todoapp.database;



import com.example.todoapp.model.Todo;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    public List<Todo> getTodos() {
        return todos;
    }

    public int getNextId() {
        return nextId++;
    }
}
