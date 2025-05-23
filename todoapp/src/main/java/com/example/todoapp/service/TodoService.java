package com.example.todoapp.service;

import com.example.todoapp.dao.TodoDaoImpl;
import com.example.todoapp.model.Todo;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TodoService {
    private TodoDaoImpl dao;

    public TodoService(TodoDaoImpl dao) {
        this.dao = dao;
    }

    public void addTodo(String title, String description, String endTime, int priority, int id) {
        Todo todo = new Todo(id, title, description, endTime, priority);
        dao.addTodo(todo);
    }

    public List<Todo> getAllTodos() {
        return dao.getAllTodos();
    }

    public List<Todo> getTodosSortedByPriority() {
        return dao.getAllTodos().stream()
                .sorted(Comparator.comparingInt(Todo::getPriority))
                .collect(Collectors.toList());
    }

    public Optional<Todo> getTodoById(int id) {
        return dao.getTodoById(id);
    }

    public boolean updateTodo(int id, String title, String description, String endTime, int priority,
            boolean completed) {
        Optional<Todo> opt = dao.getTodoById(id);
        if (opt.isPresent()) {
            Todo todo = opt.get();
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setEndTime(endTime);
            todo.setPriority(priority);
            todo.setCompleted(completed);
            return dao.updateTodo(todo);
        }
        return false;
    }

    public boolean deleteTodo(int id) {
        return dao.deleteTodo(id);
    }
}