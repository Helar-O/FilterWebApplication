package com.example.wisercat.model;

import java.time.LocalDate;

public class DateFilter {

    private int date_id;
    private String condition;
    private LocalDate date;
    private int filter_id;

    public int getDate_id() {
        return date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(int filter_id) {
        this.filter_id = filter_id;
    }

    @Override
    public String toString() {
        return "DateFilter{" +
                "date_id=" + date_id +
                ", condition='" + condition + '\'' +
                ", date=" + date +
                ", filter_id=" + filter_id +
                '}';
    }
}
