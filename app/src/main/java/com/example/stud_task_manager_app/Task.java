package com.example.stud_task_manager_app;

public class Task {

    String title;
    String dueDate;
    String status;

    public Task(String title, String dueDate, String status) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTitle() { return title; }
    public String getDueDate() { return dueDate; }
    public String getStatus() { return status; }

    public void setTitle(String title) { this.title = title; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setStatus(String status) { this.status = status; }
}