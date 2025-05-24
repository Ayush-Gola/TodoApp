package com.example.todoapp;

import com.example.todoapp.controller.TodoController;
import com.example.todoapp.dao.TodoDaoImpl;
import com.example.todoapp.service.TodoService;

public class Main {
    public static void main(String[] args) {
        TodoDaoImpl dao = new TodoDaoImpl();
        TodoService service = new TodoService(dao);
        TodoController controller = new TodoController(service);
        controller.run();
    }
}
