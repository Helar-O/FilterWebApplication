package com.example.wisercat.dao;

import com.example.wisercat.mapper.AmountMapper;
import com.example.wisercat.mapper.DateMapper;
import com.example.wisercat.mapper.FilterMapper;
import com.example.wisercat.mapper.TitleMapper;
import com.example.wisercat.model.AmountFilter;
import com.example.wisercat.model.DateFilter;
import com.example.wisercat.model.Filter;
import com.example.wisercat.model.TitleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Connection connection;

    private static final String SQL_GET_ALL_FILTERS = "SELECT * FROM filter";
    private static final String SQL_ADD_NEW_FILTER = "INSERT INTO filter(name, selection) VALUES (?, ?)";
    private static final String SQL_GET_AMOUNT_FILTERS = "SELECT * FROM amountFilter WHERE filter_id = ?";
    private static final String SQL_GET_TITLE_FILTERS = "SELECT * FROM titleFilter WHERE filter_id = ?";
    private static final String SQL_GET_DATE_FILTERS = "SELECT * FROM dateFilter WHERE filter_id = ?";
    private static final String SQL_REMOVE_FILTER_BY_ID = "DELETE FROM filter WHERE filter_id = ?";

    public ArrayList<Pair<Filter, Object>> getAllFilters() {
        ArrayList<Pair<Filter, Object>> filterPair = new ArrayList<>();
        List<Filter> filters = jdbcTemplate.query(SQL_GET_ALL_FILTERS, new FilterMapper());
        for (Filter filter : filters) {
            List<Object> criteria = new ArrayList<>();
            List<AmountFilter> amountFilters = getAllAmountFilters(filter.getFilter_id());
            List<TitleFilter> titleFilters = getAllTitleFilters(filter.getFilter_id());
            List<DateFilter> dateFilters = getAllDateFilters(filter.getFilter_id());
            if (!amountFilters.isEmpty())
                criteria.addAll(amountFilters);
            if (!titleFilters.isEmpty())
                criteria.addAll(titleFilters);
            if (!dateFilters.isEmpty())
                criteria.addAll(dateFilters);
            filterPair.add(Pair.of(filter, criteria));
        }
        return filterPair;
    }

    public List<AmountFilter> getAllAmountFilters(int filter_id) {
        return jdbcTemplate.query(SQL_GET_AMOUNT_FILTERS, new AmountMapper(), filter_id);
    }

    public List<TitleFilter> getAllTitleFilters(int filter_id) {
        return jdbcTemplate.query(SQL_GET_TITLE_FILTERS, new TitleMapper(), filter_id);
    }

    public List<DateFilter> getAllDateFilters(int filter_id) {
        return jdbcTemplate.query(SQL_GET_DATE_FILTERS, new DateMapper(), filter_id);
    }

    public int addNewFilter(Filter filter) throws SQLException {
        String[] key = {"filter_id"};

        PreparedStatement ps = connection.prepareStatement(SQL_ADD_NEW_FILTER, key);
        ps.setString(1, filter.getName());
        ps.setInt(2, filter.getSelection());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public void removeFilterById(int filter_id) {
        jdbcTemplate.update(SQL_REMOVE_FILTER_BY_ID, filter_id);
    }
}
