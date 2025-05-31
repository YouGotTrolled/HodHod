package com.example.hodhod;

public class TimedTask extends Task {
    private String[] timeAndDate; //year_month_day_hour_minute

    // Constructor
    public TimedTask(String taskTitle, String taskDetail, String timeAndDate) {
        super(taskTitle, taskDetail);
        String[] temp = timeAndDate.split("_");
        if (temp.length == 3){
            this.timeAndDate = new String[]{temp[0],temp[1],temp[2],"12","00"};
        }else if(temp.length == 5){
            this.timeAndDate = temp;
        }
    }
    public TimedTask(String taskTitle, String timeAndDate) {
        this(taskTitle,"",timeAndDate);
    }

    // Getter and Setter
    public String[] getTimeAndDate() {
        return timeAndDate;
    }
    public void setTimeAndDate(String[] timeAndDate) {
        this.timeAndDate = timeAndDate;
    }
}

