package com.example.todoapp;

import com.example.todoapp.controller.TodoController;
import com.example.todoapp.dao.TodoDaoImpl;
import com.example.todoapp.database.Database;
import com.example.todoapp.service.TodoService;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        TodoDaoImpl dao = new TodoDaoImpl(db);
        TodoService service = new TodoService(dao);
        TodoController controller = new TodoController(service);
        controller.run();
    }
}
