package com.example.trabalhodsd.model;

public class RequestTarefa {

    private String urlCalendar;

    public RequestTarefa(String urlMoodle) {
        this.urlCalendar = urlMoodle;
    }

    public String getUrlCalendar() {
        return urlCalendar;
    }

    public void setUrlCalendar(String urlCalendars) {
        this.urlCalendar = urlCalendars;
    }
}
