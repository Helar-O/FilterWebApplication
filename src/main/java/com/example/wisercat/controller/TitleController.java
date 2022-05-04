package com.example.wisercat.controller;

import com.example.wisercat.dao.TitleDao;
import com.example.wisercat.model.TitleFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TitleController {

    private final TitleDao titleDao;

    public TitleController(TitleDao titleDao) {
        this.titleDao = titleDao;
    }

    @PostMapping("/title/new")
    public int addNewTitleFilter(@RequestBody TitleFilter titleFilter) {
        return titleDao.addNewTitleFilter(titleFilter);
    }
}
