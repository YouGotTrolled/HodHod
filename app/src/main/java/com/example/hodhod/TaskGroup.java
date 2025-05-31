package com.example.hodhod;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class TaskGroup {
    private List<Task> tasks;
    private String name;

    // Constructor
    public TaskGroup(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }
    public TaskGroup(String name) {
        this(name ,new ArrayList<>());
    }

    // Getter and Setter
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

