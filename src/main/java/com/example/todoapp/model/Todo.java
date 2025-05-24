package com.example.todoapp.model;

public class Todo {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private String endTime; // e.g. "2024-06-01 18:00"
    private int priority; // 1 = highest, larger number = lower priority

    public Todo(int id, String title, String description, String endTime, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.endTime = endTime;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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