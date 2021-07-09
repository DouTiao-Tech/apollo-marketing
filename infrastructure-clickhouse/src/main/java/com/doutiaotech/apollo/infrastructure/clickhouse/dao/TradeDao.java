package com.doutiaotech.apollo.infrastructure.clickhouse.dao;

import com.doutiaotech.apollo.infrastructure.clickhouse.model.Trade;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TradeDao extends PagingAndSortingRepository<Trade, Long> {
}
