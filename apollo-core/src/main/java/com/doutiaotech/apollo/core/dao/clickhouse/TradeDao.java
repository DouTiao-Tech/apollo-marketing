package com.doutiaotech.apollo.core.dao.clickhouse;

import com.doutiaotech.apollo.core.model.clickhouse.Trade;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TradeDao extends PagingAndSortingRepository<Trade, Long> {
}
