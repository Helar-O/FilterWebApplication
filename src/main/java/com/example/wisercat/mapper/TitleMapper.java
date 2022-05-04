package com.example.wisercat.mapper;

import com.example.wisercat.model.TitleFilter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TitleMapper implements RowMapper<TitleFilter> {

    @Override
    public TitleFilter mapRow(ResultSet rs, int rowNum) throws SQLException {
        TitleFilter titleFilter = new TitleFilter();

        titleFilter.setTitle_id(rs.getInt("title_id"));
        titleFilter.setCondition(rs.getString("condition"));
        titleFilter.setTitle(rs.getString("title"));
        titleFilter.setFilter_id(rs.getInt("filter_id"));

        return titleFilter;
    }
}
