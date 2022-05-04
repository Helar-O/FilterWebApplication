package com.example.wisercat.model;

public class TitleFilter {

    private int title_id;
    private String condition;
    private String title;
    private int filter_id;

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(int filter_id) {
        this.filter_id = filter_id;
    }

    @Override
    public String toString() {
        return "TitleFilter{" +
                "title_id=" + title_id +
                ", condition='" + condition + '\'' +
                ", title='" + title + '\'' +
                ", filter_id=" + filter_id +
                '}';
    }
}
