package com.example.wisercat.mapper;

import com.example.wisercat.model.DateFilter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DateMapper implements RowMapper<DateFilter> {

    @Override
    public DateFilter mapRow(ResultSet rs, int rowNum) throws SQLException {
        DateFilter dateFilter = new DateFilter();

        dateFilter.setDate_id(rs.getInt("date_id"));
        dateFilter.setCondition(rs.getString("condition"));
        dateFilter.setDate(rs.getDate("date").toLocalDate());
        dateFilter.setFilter_id(rs.getInt("filter_id"));

        return dateFilter;
    }
}
