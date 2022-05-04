package com.example.wisercat.mapper;

import com.example.wisercat.model.Filter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilterMapper implements RowMapper<Filter> {

    @Override
    public Filter mapRow(ResultSet rs, int rowNum) throws SQLException {
        Filter filter = new Filter();

        filter.setFilter_id(rs.getInt("filter_id"));
        filter.setName(rs.getString("name"));
        filter.setSelection(rs.getInt("selection"));

        return filter;
    }
}
