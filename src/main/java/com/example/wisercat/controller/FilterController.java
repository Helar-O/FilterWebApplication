package com.example.wisercat.controller;

import com.example.wisercat.dao.FilterDao;
import com.example.wisercat.model.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilterController {

    private final FilterDao filterDao;

    @Autowired
    public FilterController(FilterDao filterDao) {
        this.filterDao = filterDao;
    }

    @GetMapping("/filter/all")
    public ArrayList<Pair<Filter, Object>> getAllFilters() {
        return filterDao.getAllFilters();
    }

    @PostMapping("/filter/new")
    public int addNewFilter(@RequestBody Filter filter) throws SQLException {
        return filterDao.addNewFilter(filter);
    }

    @PostMapping("/filter/remove/{filter_id}")
    public void removeFilter(@PathVariable int filter_id) {
        filterDao.removeFilterById(filter_id);
    }
}
