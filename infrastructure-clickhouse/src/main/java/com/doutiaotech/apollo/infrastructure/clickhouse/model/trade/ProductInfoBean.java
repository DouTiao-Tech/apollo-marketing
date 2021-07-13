package com.doutiaotech.apollo.infrastructure.clickhouse.model.trade;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductInfoBean {
    private String product_name;
    private int price;
    private String outer_sku_id;
    private int sku_id;
    private int product_count;
    private long product_id;
    private String sku_order_id;
    private List<SkuSpecsBean> sku_specs;

    @Data
    @NoArgsConstructor
    public static class SkuSpecsBean {
        private String name;
        private String value;
    }
}
