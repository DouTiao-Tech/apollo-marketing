package com.doutiaotech.apollo.infrastructure.clickhouse.model.trade;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LogisticsInfoBean {
    private String tracking_no;
    private String company;
    private int ship_time;
    private String delivery_id;
    private String company_name;
    private List<ProductInfoBean> product_info;
}
