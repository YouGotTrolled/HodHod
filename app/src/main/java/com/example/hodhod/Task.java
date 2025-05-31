package com.example.hodhod;

public class Task {
    private String taskTitle;
    private String taskDetail;

    // Constructor
    public Task(String taskTitle, String taskDetail) {
        this.taskTitle = taskTitle;
        this.taskDetail = taskDetail;
    }
    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.taskDetail = "";
    }

    // Getter and Setter
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    public String getTaskDetail() {
        return taskDetail;
    }
    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }
    public boolean equals(Object o){
        boolean result = false;
        if(o instanceof Task){
            Task temp = ((Task) o);
            if(temp.getTaskTitle().equals(this.getTaskTitle()))
                result = true;
        }
        return result;
    }
    public String toString(){
        return this.getTaskTitle();
    }
}

