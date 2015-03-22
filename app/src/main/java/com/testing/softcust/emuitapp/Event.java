package com.testing.softcust.emuitapp;

/**
 * Created by LENOVO on 2/26/2015.
 */
public class Event {
    private String event_id;
    private String event_name;
    private String event_type;
    private String start_date;
    private String end_date;
    private String description;
    private String event_sem_id;

    public Event(){
        this.event_id = "";
        this.event_name = "";
        this.event_type = "";
        this.start_date = "";
        this.end_date = "";
        this.description = "";
        this.event_sem_id = "";
    }

    public Event(String event_id, String event_name, String event_type, String start_date, String end_date, String description, String event_sem_id){
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_type = event_type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.event_sem_id = event_sem_id;
    }

    public Event(String event_id){
        this.event_id = event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEvent_sem_id(String event_sem_id) {
        this.event_sem_id = event_sem_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getDescription() {
        return description;
    }

    public String getEvent_sem_id() {
        return event_sem_id;
    }
}
