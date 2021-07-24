package com.doutiaotech.apollo.infrastructure.clickhouse.mapper;

import com.doutiaotech.apollo.infrastructure.clickhouse.BaseDaoTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeMapperTest extends BaseDaoTest {

    @Autowired
    TradeMapper tradeMapper;

    @Test
    public void test_findById() {
        // Trade trade = tradeMapper.findById(0L);
        // assertThat(trade, isNull());
    }

}
