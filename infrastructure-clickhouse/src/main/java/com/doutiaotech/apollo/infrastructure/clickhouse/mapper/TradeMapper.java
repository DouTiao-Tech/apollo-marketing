package com.doutiaotech.apollo.infrastructure.clickhouse.mapper;

import com.doutiaotech.apollo.infrastructure.clickhouse.model.trade.Trade;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TradeMapper {
    @Select("select * from trade where order_id=#{orderId}")
    Trade findById(Long orderId);
}
