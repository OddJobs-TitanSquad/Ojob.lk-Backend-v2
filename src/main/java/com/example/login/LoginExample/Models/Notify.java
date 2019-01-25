package com.example.login.LoginExample.Models;

public class Notify {

    private String job;
    private String notification;
    private String user;


    public Notify() {

    }

    public Notify( String job, String notification) {

        this.job = job;
        this.notification = notification;
    }





    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }


}
