package com.doutiaotech.apollo.infrastructure.clickhouse.model.trade;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SkuOrderListBean {
    private String order_id;
    private String parent_order_id;
    private int order_level;
    private int biz;
    private String biz_desc;
    private int order_type;
    private String order_type_desc;
    private int trade_type;
    private String trade_type_desc;
    private int order_status;
    private String order_status_desc;
    private int main_status;
    private String main_status_desc;
    private int pay_time;
    private int order_expire_time;
    private int finish_time;
    private int create_time;
    private int update_time;
    private String cancel_reason;
    private int b_type;
    private String b_type_desc;
    private int sub_b_type;
    private String sub_b_type_desc;
    private int send_pay;
    private String send_pay_desc;
    private int author_id;
    private String author_name;
    private String theme_type;
    private String theme_type_desc;
    private long app_id;
    private long room_id;
    private String content_id;
    private String video_id;
    private String origin_id;
    private long cid;
    private int c_biz;
    private String c_biz_desc;
    private long page_id;
    private int pay_type;
    private String channel_payment_no;
    private int order_amount;
    private int pay_amount;
    private int post_insurance_amount;
    private int modify_amount;
    private int modify_post_amount;
    private int promotion_amount;
    private int promotion_shop_amount;
    private int promotion_platform_amount;
    private int shop_cost_amount;
    private int platform_cost_amount;
    private int promotion_talent_amount;
    private int promotion_pay_amount;
    private String code;
    private String post_tel;
    private String post_receiver;
    private PostAddrBean post_addr;
    private int exp_ship_time;
    private int ship_time;
    private int logistics_receipt_time;
    private int confirm_receipt_time;
    private int goods_type;
    private long product_id;
    private int sku_id;
    private int first_cid;
    private int second_cid;
    private int third_cid;
    private int fourth_cid;
    private String out_sku_id;
    private String supplier_id;
    private String out_product_id;
    private String inventory_type;
    private String inventory_type_desc;
    private int reduce_stock_type;
    private String reduce_stock_type_desc;
    private int origin_amount;
    private boolean has_tax;
    private int item_num;
    private int sum_amount;
    private String source_platform;
    private String product_pic;
    private int is_comment;
    private String product_name;
    private int post_amount;
    private int pre_sale_type;
    private AfterSaleInfoBean after_sale_info;
    private List<SpecBean> spec;
    private List<String> warehouse_ids;
    private List<String> out_warehouse_ids;
    private List<InventoryListBean> inventory_list;

    @Data
    @NoArgsConstructor
    public static class AfterSaleInfoBean {
        private int after_sale_status;
        private int after_sale_type;
        private int refund_status;
    }

    @Data
    @NoArgsConstructor
    public static class SpecBean {
        private String name;
        private String value;
    }

    @Data
    @NoArgsConstructor
    public static class InventoryListBean {
        private String warehouse_id;
        private String out_warehouse_id;
        private int inventory_type;
        private String inventory_type_desc;
        private int count;
    }
}
