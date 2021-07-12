package com.doutiaotech.apollo.web.controller;

import com.doutiaotech.apollo.infrastructure.clickhouse.mapper.TradeMapper;
import com.doutiaotech.apollo.infrastructure.clickhouse.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeMapper tradeMapper;

    @GetMapping("/{id}")
    public Trade findOne(@PathVariable Long id) {
        return tradeMapper.findById(id);
    }

}
