package com.doutiaotech.apollo.infrastructure.clickhouse.mapper;

import com.doutiaotech.apollo.infrastructure.clickhouse.model.Trade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeMapper {
    Trade findById(Long id);
}