package com.example.todoapp.service;

import com.example.todoapp.dao.TodoDaoImpl;
import com.example.todoapp.dto.TodoDTO;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TodoService {
    private TodoDaoImpl dao;

    public TodoService(TodoDaoImpl dao) {
        this.dao = dao;
    }

    public void addTodo(String title, String description, String endTime, int priority) {
        int id = dao.getNextId();
        TodoDTO todo = new TodoDTO(id, title, description, false, endTime, priority);
        dao.addTodo(todo);
    }

    public List<TodoDTO> getAllTodos() {
        return dao.getAllTodos();
    }

    public List<TodoDTO> getTodosSortedByPriority() {
        return dao.getAllTodos().stream()
                .sorted(Comparator.comparingInt(TodoDTO::getPriority))
                .collect(Collectors.toList());
    }

    public Optional<TodoDTO> getTodoById(int id) {
        return dao.getTodoById(id);
    }

    public boolean updateTodo(int id, String title, String description, String endTime, int priority,
            boolean completed) {
        Optional<TodoDTO> opt = dao.getTodoById(id);
        if (opt.isPresent()) {
            TodoDTO todo = opt.get();
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
    
    public List<TodoDTO> searchTodos(String keyword) {
        return dao.searchTodos(keyword);
    }
}