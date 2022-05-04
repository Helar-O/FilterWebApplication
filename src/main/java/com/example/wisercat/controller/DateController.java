package com.example.wisercat.controller;

import com.example.wisercat.dao.DateDao;
import com.example.wisercat.model.DateFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DateController {

    private final DateDao dateDao;

    public DateController(DateDao dateDao) {
        this.dateDao = dateDao;
    }

    @PostMapping("/date/new")
    public int addNewDateFilter(@RequestBody DateFilter dateFilter) {
        return dateDao.addNewDateFilter(dateFilter);
    }
}
