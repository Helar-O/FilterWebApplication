package com.example.wisercat.controller;

import com.example.wisercat.dao.AmountDao;
import com.example.wisercat.model.AmountFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AmountController {

    private final AmountDao amountDao;

    public AmountController(AmountDao amountDao) {
        this.amountDao = amountDao;
    }

    @PostMapping("/amount/new")
    public int addNewAmountFilter(@RequestBody AmountFilter amountFilter) {
        return amountDao.addNewAmountFilter(amountFilter);
    }
}
