package com.example.wisercat.dao;

import com.example.wisercat.model.DateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_ADD_NEW_DATE_FILTER = "INSERT INTO dateFilter(condition, date, filter_id) VALUES(?, ?, ?)";

    public int addNewDateFilter(DateFilter dateFilter) {
        return jdbcTemplate.update(
                SQL_ADD_NEW_DATE_FILTER,
                dateFilter.getCondition(),
                dateFilter.getDate(),
                dateFilter.getFilter_id());
    }
}
