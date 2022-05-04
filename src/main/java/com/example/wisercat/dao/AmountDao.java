package com.example.wisercat.dao;

import com.example.wisercat.model.AmountFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AmountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_ADD_NEW_AMOUNT_FILTER = "INSERT INTO amountFilter(condition, amount, filter_id) VALUES(?, ?, ?)";

    public int addNewAmountFilter(AmountFilter amountFilter) {
        return jdbcTemplate.update(
                SQL_ADD_NEW_AMOUNT_FILTER,
                amountFilter.getCondition(),
                amountFilter.getAmount(),
                amountFilter.getFilter_id());
    }
}
