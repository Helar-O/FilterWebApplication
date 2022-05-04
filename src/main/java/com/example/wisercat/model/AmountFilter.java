package com.example.wisercat.model;

public class AmountFilter {

    private int amount_id;
    private String condition;
    private int amount;
    private int filter_id;

    public int getAmount_id() {
        return amount_id;
    }

    public void setAmount_id(int amount_id) {
        this.amount_id = amount_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(int filter_id) {
        this.filter_id = filter_id;
    }

    @Override
    public String toString() {
        return "AmountFilter{" +
                "amount_id=" + amount_id +
                ", condition='" + condition + '\'' +
                ", amount=" + amount +
                ", filter_id=" + filter_id +
                '}';
    }
}
