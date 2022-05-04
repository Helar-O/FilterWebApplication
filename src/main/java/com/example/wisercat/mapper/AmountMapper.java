package com.example.wisercat.mapper;

import com.example.wisercat.model.AmountFilter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AmountMapper implements RowMapper<AmountFilter> {

    @Override
    public AmountFilter mapRow(ResultSet rs, int rowNum) throws SQLException {
        AmountFilter amountFilter = new AmountFilter();

        amountFilter.setAmount_id(rs.getInt("amount_id"));
        amountFilter.setCondition(rs.getString("condition"));
        amountFilter.setAmount(rs.getInt("amount"));
        amountFilter.setFilter_id(rs.getInt("filter_id"));

        return amountFilter;
    }
}
