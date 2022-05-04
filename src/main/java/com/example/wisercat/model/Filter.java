package com.example.wisercat.model;


public class Filter {

    private int filter_id;
    private String name;
    private int selection;

    public int getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(int filter_id) {
        this.filter_id = filter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "filter_id=" + filter_id +
                ", name='" + name + '\'' +
                ", selection=" + selection +
                '}';
    }
}
