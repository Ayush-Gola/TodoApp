package com.example.todoapp.dao;



import com.example.todoapp.database.Database;
import com.example.todoapp.model.Todo;
import java.util.List;
import java.util.Optional;

public class TodoDaoImpl {
    private Database db;

    public TodoDaoImpl(Database db) {
        this.db = db;
    }

    public void addTodo(Todo todo) {
        db.getTodos().add(todo);
    }

    public List<Todo> getAllTodos() {
        return db.getTodos();
    }

    public Optional<Todo> getTodoById(int id) {
        return db.getTodos().stream().filter(t -> t.getId() == id).findFirst();
    }

    public boolean updateTodo(Todo todo) {
        Optional<Todo> opt = getTodoById(todo.getId());
        if (opt.isPresent()) {
            Todo t = opt.get();
            t.setTitle(todo.getTitle());
            t.setDescription(todo.getDescription());
            t.setCompleted(todo.isCompleted());
            return true;
        }
        return false;
    }

    public boolean deleteTodo(int id) {
        return db.getTodos().removeIf(t -> t.getId() == id);
    }
}
