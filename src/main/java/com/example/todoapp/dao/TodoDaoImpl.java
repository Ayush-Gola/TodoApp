package com.example.todoapp.dao;

import com.example.todoapp.dto.TodoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TodoDaoImpl {
    private static final String FILE_PATH = "todos.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<TodoDTO> todos;

    public TodoDaoImpl() {
        this.todos = loadTodos();
    }

    private List<TodoDTO> loadTodos() {
        File file = new File(FILE_PATH);
        if (!file.exists())
            return new ArrayList<>();
        try {
            return mapper.readValue(file, new TypeReference<List<TodoDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveTodos() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), todos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TodoDTO> getAllTodos() {
        return new ArrayList<>(todos);
    }

    public List<TodoDTO> searchTodos(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        List<TodoDTO> result = new ArrayList<>();
        for (TodoDTO todo : todos) {
            if (todo.getTitle().toLowerCase().contains(lowerKeyword) ||
                    todo.getDescription().toLowerCase().contains(lowerKeyword)) {
                result.add(todo);
            }
        }
        return result;
    }

    public Optional<TodoDTO> getTodoById(int id) {
        return todos.stream().filter(t -> t.getId() == id).findFirst();
    }

    public void addTodo(TodoDTO todo) {
        todos.add(todo);
        saveTodos();
    }

    public boolean updateTodo(TodoDTO todo) {
        Optional<TodoDTO> opt = getTodoById(todo.getId());
        if (opt.isPresent()) {
            TodoDTO t = opt.get();
            t.setTitle(todo.getTitle());
            t.setDescription(todo.getDescription());
            t.setCompleted(todo.isCompleted());
            t.setEndTime(todo.getEndTime());
            t.setPriority(todo.getPriority());
            saveTodos();
            return true;
        }
        return false;
    }

    public boolean deleteTodo(int id) {
        boolean removed = todos.removeIf(t -> t.getId() == id);
        if (removed)
            saveTodos();
        return removed;
    }

    public int getNextId() {
        return todos.stream().mapToInt(TodoDTO::getId).max().orElse(0) + 1;
    }
}