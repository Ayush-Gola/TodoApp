package com.example.todoapp.dto;

public class TodoDTO {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private String endTime;
    private int priority;

    public TodoDTO() {
    } // Default constructor for Jackson

    public TodoDTO(int id, String title, String description, boolean completed, String endTime, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.endTime = endTime;
        this.priority = priority;
    }

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", endTime='" + endTime + '\'' +
                ", priority=" + priority +
                '}';
    }
}