package com.example.wisercat.dao;

import com.example.wisercat.model.TitleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TitleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_ADD_NEW_TITLE_FILTER = "INSERT INTO titleFilter(condition, title, filter_id) VALUES(?, ?, ?)";

    public int addNewTitleFilter(TitleFilter titleFilter) {
        return jdbcTemplate.update(
                SQL_ADD_NEW_TITLE_FILTER,
                titleFilter.getCondition(),
                titleFilter.getTitle(),
                titleFilter.getFilter_id());
    }
}
