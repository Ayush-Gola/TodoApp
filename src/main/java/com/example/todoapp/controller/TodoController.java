package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoDTO;
import com.example.todoapp.service.TodoService;
import com.example.todoapp.util.InputValidator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TodoController {
    private final TodoService service;
    private final Scanner scanner = new Scanner(System.in);

    public TodoController(TodoService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Todo App Menu ---");
            System.out.println("1. Add Todo");
            System.out.println("2. View All Todos");
            System.out.println("3. Update Todo");
            System.out.println("4. Delete Todo");
            System.out.println("5. Mark Todo as Completed");
            System.out.println("6. View Todos Sorted by Priority");
            System.out.println("7. Search Todos by Title/Description");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            String choiceInput = scanner.nextLine();
            int choice = InputValidator.isValidId(choiceInput) ? Integer.parseInt(choiceInput) : -1;

            switch (choice) {
                case 1 -> addTodo();
                case 2 -> viewAllTodos();
                case 3 -> updateTodo();
                case 4 -> deleteTodo();
                case 5 -> markCompleted();
                case 6 -> viewTodosSortedByPriority();
                case 7 -> searchTodos();
                case 8 -> {
                    System.out.println("Exiting Todo App. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addTodo() {
        String title;
        do {
            System.out.print("Enter title: ");
            title = scanner.nextLine();
            if (!InputValidator.isNonEmpty(title))
                System.out.println("Title cannot be empty.");
        } while (!InputValidator.isNonEmpty(title));

        String desc;
        do {
            System.out.print("Enter description: ");
            desc = scanner.nextLine();
            if (!InputValidator.isNonEmpty(desc))
                System.out.println("Description cannot be empty.");
        } while (!InputValidator.isNonEmpty(desc));

        String endTime;
        do {
            System.out.print("Enter end time (yyyy-MM-dd HH:mm): ");
            endTime = scanner.nextLine();
            if (!InputValidator.isValidEndTime(endTime))
                System.out.println("Invalid date/time format.");
        } while (!InputValidator.isValidEndTime(endTime));

        String priorityInput;
        do {
            System.out.print("Enter priority (1=highest): ");
            priorityInput = scanner.nextLine();
            if (!InputValidator.isValidPriority(priorityInput))
                System.out.println("Priority must be a positive integer.");
        } while (!InputValidator.isValidPriority(priorityInput));
        int priority = Integer.parseInt(priorityInput);

        service.addTodo(title, desc, endTime, priority);
        System.out.println("Todo added successfully!");
    }

    private void viewAllTodos() {
        List<TodoDTO> todos = service.getAllTodos();
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            todos.forEach(System.out::println);
        }
    }

    private void viewTodosSortedByPriority() {
        List<TodoDTO> todos = service.getTodosSortedByPriority();
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            System.out.println("--- Todos Sorted by Priority ---");
            todos.forEach(System.out::println);
        }
    }

    private void updateTodo() {
        String idInput;
        do {
            System.out.print("Enter Todo ID to update: ");
            idInput = scanner.nextLine();
            if (!InputValidator.isValidId(idInput))
                System.out.println("Invalid ID.");
        } while (!InputValidator.isValidId(idInput));
        int id = Integer.parseInt(idInput);

        Optional<TodoDTO> opt = service.getTodoById(id);
        if (opt.isPresent()) {
            String title;
            do {
                System.out.print("Enter new title: ");
                title = scanner.nextLine();
                if (!InputValidator.isNonEmpty(title))
                    System.out.println("Title cannot be empty.");
            } while (!InputValidator.isNonEmpty(title));

            String desc;
            do {
                System.out.print("Enter new description: ");
                desc = scanner.nextLine();
                if (!InputValidator.isNonEmpty(desc))
                    System.out.println("Description cannot be empty.");
            } while (!InputValidator.isNonEmpty(desc));

            String endTime;
            do {
                System.out.print("Enter new end time (yyyy-MM-dd HH:mm): ");
                endTime = scanner.nextLine();
                if (!InputValidator.isValidEndTime(endTime))
                    System.out.println("Invalid date/time format.");
            } while (!InputValidator.isValidEndTime(endTime));

            String priorityInput;
            do {
                System.out.print("Enter new priority (1=highest): ");
                priorityInput = scanner.nextLine();
                if (!InputValidator.isValidPriority(priorityInput))
                    System.out.println("Priority must be a positive integer.");
            } while (!InputValidator.isValidPriority(priorityInput));
            int priority = Integer.parseInt(priorityInput);

            String completedInput;
            do {
                System.out.print("Is completed? (true/false): ");
                completedInput = scanner.nextLine();
                if (!InputValidator.isValidBoolean(completedInput))
                    System.out.println("Enter true or false.");
            } while (!InputValidator.isValidBoolean(completedInput));
            boolean completed = Boolean.parseBoolean(completedInput);

            if (service.updateTodo(id, title, desc, endTime, priority, completed)) {
                System.out.println("Todo updated successfully!");
            } else {
                System.out.println("Update failed.");
            }
        } else {
            System.out.println("Todo not found.");
        }
    }

    private void deleteTodo() {
        String idInput;
        do {
            System.out.print("Enter Todo ID to delete: ");
            idInput = scanner.nextLine();
            if (!InputValidator.isValidId(idInput))
                System.out.println("Invalid ID.");
        } while (!InputValidator.isValidId(idInput));
        int id = Integer.parseInt(idInput);

        if (service.deleteTodo(id)) {
            System.out.println("Todo deleted successfully!");
        } else {
            System.out.println("Todo not found.");
        }
    }

    private void markCompleted() {
        String idInput;
        do {
            System.out.print("Enter Todo ID to mark as completed: ");
            idInput = scanner.nextLine();
            if (!InputValidator.isValidId(idInput))
                System.out.println("Invalid ID.");
        } while (!InputValidator.isValidId(idInput));
        int id = Integer.parseInt(idInput);

        Optional<TodoDTO> opt = service.getTodoById(id);
        if (opt.isPresent()) {
            TodoDTO todo = opt.get();
            if (!todo.isCompleted()) {
                todo.setCompleted(true);
                service.updateTodo(id, todo.getTitle(), todo.getDescription(), todo.getEndTime(), todo.getPriority(),
                        true);
                System.out.println("Todo marked as completed!");
            } else {
                System.out.println("Todo is already completed.");
            }
        } else {
            System.out.println("Todo not found.");
        }
    }
    
    private void searchTodos() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        List<TodoDTO> results = service.searchTodos(keyword);
        if (results.isEmpty()) {
            System.out.println("No todos found matching the keyword.");
        } else {
            System.out.println("--- Search Results ---");
            results.forEach(System.out::println);
        }
    }
}