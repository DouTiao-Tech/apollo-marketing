package com.doutiaotech.apollo.external.dy.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * https://op.jinritemai.com/docs/api-docs/15/555
 */
@Data
@NoArgsConstructor
public class TradeSearchPage {

    /**
     * page : 0
     * total : 1000
     * size : 20
     * shop_order_list : [{"shop_id":77977,"shop_name":"刘好好严选","open_id":"23431434","order_id":"4781320682406083640","order_level":2,"biz":1,"biz_desc":"鲁班","order_type":0,"order_type_desc":"普通订单","trade_type":1,"trade_type_desc":"拼团","order_status":3,"order_status_desc":"待支付","main_status":103,"main_status_desc":"部分支付","pay_time":1617355413,"order_expire_time":1800,"finish_time":1617355413,"create_time":1617355413,"update_time":1617355413,"cancel_reason":"不想要","buyer_words":"要xxx","seller_words":"注意XX","b_type":1,"b_type_desc":"抖音","sub_b_type":1,"sub_b_type_desc":"小程序","app_id":43545454556,"pay_type":1,"channel_payment_no":"PAY234532534534","order_amount":600,"pay_amount":500,"post_amount":10,"post_insurance_amount":10,"modify_amount":-10,"modify_post_amount":-1,"promotion_amount":1000,"promotion_shop_amount":100,"promotion_platform_amount":100,"shop_cost_amount":100,"platform_cost_amount":100,"promotion_talent_amount":100,"promotion_pay_amount":10,"post_tel":"12345678911","post_receiver":"张三","post_addr":{"province":{"name":"北京市","id":"110000"},"city":{"name":"市辖区","id":"110000"},"town":{"name":"海淀区","id":"110000"},"street":{"name":"中关村街道","id":"110000"},"detail":"丹棱街1号"},"exp_ship_time":1617355413,"ship_time":1617355413,"logistics_info":[{"tracking_no":"3617355413","company":"shunfeng","ship_time":1617355413,"delivery_id":"shunfeng_3617355413","company_name":"顺丰","product_info":[{"product_name":"苹果","price":1000,"outer_sku_id":"sdfa","sku_id":3254535,"sku_specs":[{"name":"颜色","value":"蓝"}],"product_count":2,"product_id":3473196049974326300,"sku_order_id":"4781320682406083640"}]}],"sku_order_list":[{"order_id":"4781320682406083640","parent_order_id":"4781320682406083640","order_level":3,"biz":1,"biz_desc":"鲁班","order_type":0,"order_type_desc":"普通订单","trade_type":1,"trade_type_desc":"拼团","order_status":3,"order_status_desc":"待支付","main_status":103,"main_status_desc":"部分支付","pay_time":1617355413,"order_expire_time":1800,"finish_time":1617355413,"create_time":1617355413,"update_time":1617355413,"cancel_reason":"不想要","b_type":1,"b_type_desc":"抖音","sub_b_type":1,"sub_b_type_desc":"小程序","send_pay":1,"send_pay_desc":"鲁班广告","author_id":324234453,"author_name":"XXXX","theme_type":"1","theme_type_desc":"直播间","app_id":43545454556,"room_id":43545454556,"content_id":"43545454556","video_id":"43545454556","origin_id":"43545454556","cid":43545454556,"c_biz":1,"c_biz_desc":"鲁班广告","page_id":43545454556,"pay_type":1,"channel_payment_no":"PAY234532534534","order_amount":600,"pay_amount":500,"post_insurance_amount":10,"modify_amount":-10,"modify_post_amount":-1,"promotion_amount":1000,"promotion_shop_amount":100,"promotion_platform_amount":100,"shop_cost_amount":100,"platform_cost_amount":100,"promotion_talent_amount":100,"promotion_pay_amount":10,"code":"djfafj","post_tel":"12345678911","post_receiver":"张三","post_addr":{"province":{"name":"北京市","id":"110000"},"city":{"name":"市辖区","id":"110000"},"town":{"name":"海淀区","id":"110000"},"street":{"name":"中关村街道","id":"110000"},"detail":"丹棱街1号"},"exp_ship_time":1617355413,"ship_time":1617355413,"logistics_receipt_time":1617355413,"confirm_receipt_time":1617355413,"goods_type":1,"product_id":3473196049974326300,"sku_id":3254535,"spec":[{"name":"颜色","value":"蓝"}],"first_cid":20005,"second_cid":20174,"third_cid":20174,"fourth_cid":20174,"out_sku_id":"43564553","supplier_id":"fsgfgsdg","out_product_id":"432655662343","warehouse_ids":["49574835439","954764056"],"out_warehouse_ids":["49574835439","954764056"],"inventory_type":"2","inventory_type_desc":"普通库存","reduce_stock_type":1,"reduce_stock_type_desc":"下单减库存","origin_amount":100,"has_tax":true,"item_num":2,"sum_amount":200,"source_platform":"XXX","product_pic":"https:xxxxx","is_comment":1,"product_name":"衣服","inventory_list":[{"warehouse_id":"3141234234324","out_warehouse_id":"3254234234234","inventory_type":1,"inventory_type_desc":"普通库存","count":2}],"post_amount":1,"pre_sale_type":1,"after_sale_info":{"after_sale_status":6,"after_sale_type":1,"refund_status":1}}],"seller_remark_stars":1,"order_phase_list":[{"phase_order_id":"4781320682406083640","total_phase":2,"current_phase":1,"pay_success":true,"sku_order_id":"4781320682406083640","campaign_id":3214324342342,"phase_payable_price":100,"phase_pay_type":1,"phase_open_time":1617355413,"phase_pay_time":1617355413,"phase_close_time":1617355413,"channel_payment_no":"PAY34243247134325","phase_order_amount":100,"phase_sum_amount":100,"phase_post_amount":100,"phase_pay_amount":100,"phase_promotion_amount":100,"current_phase_status_desc":"已开始但未支付","sku_price":100}],"doudian_open_id":"#zuLyd4U4J3p+czzXkwg+ZQ673h7KTcrKOddb5iPGAAE0K3MYJmgXEXof9LDtoScAfMKvdVRqpAL4CEI3SrLwYATIzTF9Qw=="}]
     */
    private int page;
    private int total;
    private int size;
    private List<ShopOrderListBean> shop_order_list;

    @Data
    @NoArgsConstructor
    public static class ShopOrderListBean {
        /**
         * shop_id : 77977
         * shop_name : 刘好好严选
         * open_id : 23431434
         * order_id : 4781320682406083640
         * order_level : 2
         * biz : 1
         * biz_desc : 鲁班
         * order_type : 0
         * order_type_desc : 普通订单
         * trade_type : 1
         * trade_type_desc : 拼团
         * order_status : 3
         * order_status_desc : 待支付
         * main_status : 103
         * main_status_desc : 部分支付
         * pay_time : 1617355413
         * order_expire_time : 1800
         * finish_time : 1617355413
         * create_time : 1617355413
         * update_time : 1617355413
         * cancel_reason : 不想要
         * buyer_words : 要xxx
         * seller_words : 注意XX
         * b_type : 1
         * b_type_desc : 抖音
         * sub_b_type : 1
         * sub_b_type_desc : 小程序
         * app_id : 43545454556
         * pay_type : 1
         * channel_payment_no : PAY234532534534
         * order_amount : 600
         * pay_amount : 500
         * post_amount : 10
         * post_insurance_amount : 10
         * modify_amount : -10
         * modify_post_amount : -1
         * promotion_amount : 1000
         * promotion_shop_amount : 100
         * promotion_platform_amount : 100
         * shop_cost_amount : 100
         * platform_cost_amount : 100
         * promotion_talent_amount : 100
         * promotion_pay_amount : 10
         * post_tel : 12345678911
         * post_receiver : 张三
         * post_addr : {"province":{"name":"北京市","id":"110000"},"city":{"name":"市辖区","id":"110000"},"town":{"name":"海淀区","id":"110000"},"street":{"name":"中关村街道","id":"110000"},"detail":"丹棱街1号"}
         * exp_ship_time : 1617355413
         * ship_time : 1617355413
         * logistics_info : [{"tracking_no":"3617355413","company":"shunfeng","ship_time":1617355413,"delivery_id":"shunfeng_3617355413","company_name":"顺丰","product_info":[{"product_name":"苹果","price":1000,"outer_sku_id":"sdfa","sku_id":3254535,"sku_specs":[{"name":"颜色","value":"蓝"}],"product_count":2,"product_id":3473196049974326300,"sku_order_id":"4781320682406083640"}]}]
         * sku_order_list : [{"order_id":"4781320682406083640","parent_order_id":"4781320682406083640","order_level":3,"biz":1,"biz_desc":"鲁班","order_type":0,"order_type_desc":"普通订单","trade_type":1,"trade_type_desc":"拼团","order_status":3,"order_status_desc":"待支付","main_status":103,"main_status_desc":"部分支付","pay_time":1617355413,"order_expire_time":1800,"finish_time":1617355413,"create_time":1617355413,"update_time":1617355413,"cancel_reason":"不想要","b_type":1,"b_type_desc":"抖音","sub_b_type":1,"sub_b_type_desc":"小程序","send_pay":1,"send_pay_desc":"鲁班广告","author_id":324234453,"author_name":"XXXX","theme_type":"1","theme_type_desc":"直播间","app_id":43545454556,"room_id":43545454556,"content_id":"43545454556","video_id":"43545454556","origin_id":"43545454556","cid":43545454556,"c_biz":1,"c_biz_desc":"鲁班广告","page_id":43545454556,"pay_type":1,"channel_payment_no":"PAY234532534534","order_amount":600,"pay_amount":500,"post_insurance_amount":10,"modify_amount":-10,"modify_post_amount":-1,"promotion_amount":1000,"promotion_shop_amount":100,"promotion_platform_amount":100,"shop_cost_amount":100,"platform_cost_amount":100,"promotion_talent_amount":100,"promotion_pay_amount":10,"code":"djfafj","post_tel":"12345678911","post_receiver":"张三","post_addr":{"province":{"name":"北京市","id":"110000"},"city":{"name":"市辖区","id":"110000"},"town":{"name":"海淀区","id":"110000"},"street":{"name":"中关村街道","id":"110000"},"detail":"丹棱街1号"},"exp_ship_time":1617355413,"ship_time":1617355413,"logistics_receipt_time":1617355413,"confirm_receipt_time":1617355413,"goods_type":1,"product_id":3473196049974326300,"sku_id":3254535,"spec":[{"name":"颜色","value":"蓝"}],"first_cid":20005,"second_cid":20174,"third_cid":20174,"fourth_cid":20174,"out_sku_id":"43564553","supplier_id":"fsgfgsdg","out_product_id":"432655662343","warehouse_ids":["49574835439","954764056"],"out_warehouse_ids":["49574835439","954764056"],"inventory_type":"2","inventory_type_desc":"普通库存","reduce_stock_type":1,"reduce_stock_type_desc":"下单减库存","origin_amount":100,"has_tax":true,"item_num":2,"sum_amount":200,"source_platform":"XXX","product_pic":"https:xxxxx","is_comment":1,"product_name":"衣服","inventory_list":[{"warehouse_id":"3141234234324","out_warehouse_id":"3254234234234","inventory_type":1,"inventory_type_desc":"普通库存","count":2}],"post_amount":1,"pre_sale_type":1,"after_sale_info":{"after_sale_status":6,"after_sale_type":1,"refund_status":1}}]
         * seller_remark_stars : 1
         * order_phase_list : [{"phase_order_id":"4781320682406083640","total_phase":2,"current_phase":1,"pay_success":true,"sku_order_id":"4781320682406083640","campaign_id":3214324342342,"phase_payable_price":100,"phase_pay_type":1,"phase_open_time":1617355413,"phase_pay_time":1617355413,"phase_close_time":1617355413,"channel_payment_no":"PAY34243247134325","phase_order_amount":100,"phase_sum_amount":100,"phase_post_amount":100,"phase_pay_amount":100,"phase_promotion_amount":100,"current_phase_status_desc":"已开始但未支付","sku_price":100}]
         * doudian_open_id : #zuLyd4U4J3p+czzXkwg+ZQ673h7KTcrKOddb5iPGAAE0K3MYJmgXEXof9LDtoScAfMKvdVRqpAL4CEI3SrLwYATIzTF9Qw==
         */
        private int shop_id;
        private String shop_name;
        private String open_id;
        private String order_id;
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
        private long pay_time;
        private long order_expire_time;
        private long finish_time;
        private long create_time;
        private long update_time;
        private String cancel_reason;
        private String buyer_words;
        private String seller_words;
        private int b_type;
        private String b_type_desc;
        private int sub_b_type;
        private String sub_b_type_desc;
        private long app_id;
        private int pay_type;
        private String channel_payment_no;
        private int order_amount;
        private int pay_amount;
        private int post_amount;
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
        private String post_tel;
        private String post_receiver;
        private PostAddrBean post_addr;
        private long exp_ship_time;
        private long ship_time;
        private int seller_remark_stars;
        private String doudian_open_id;
        private List<LogisticsInfoBean> logistics_info;
        private List<SkuOrderListBean> sku_order_list;
        private List<OrderPhaseListBean> order_phase_list;

        @Data
        @NoArgsConstructor
        public static class PostAddrBean {
            /**
             * province : {"name":"北京市","id":"110000"}
             * city : {"name":"市辖区","id":"110000"}
             * town : {"name":"海淀区","id":"110000"}
             * street : {"name":"中关村街道","id":"110000"}
             * detail : 丹棱街1号
             */

            private ProvinceBean province;
            private CityBean city;
            private TownBean town;
            private StreetBean street;
            private String detail;

            @NoArgsConstructor
            @Data
            public static class ProvinceBean {
                /**
                 * name : 北京市
                 * id : 110000
                 */

                private String name;
                private String id;
            }

            @NoArgsConstructor
            @Data
            public static class CityBean {
                /**
                 * name : 市辖区
                 * id : 110000
                 */

                private String name;
                private String id;
            }

            @NoArgsConstructor
            @Data
            public static class TownBean {
                /**
                 * name : 海淀区
                 * id : 110000
                 */

                private String name;
                private String id;
            }

            @NoArgsConstructor
            @Data
            public static class StreetBean {
                /**
                 * name : 中关村街道
                 * id : 110000
                 */

                private String name;
                private String id;
            }
        }

        @Data
        @NoArgsConstructor
        public static class LogisticsInfoBean {
            /**
             * tracking_no : 3617355413
             * company : shunfeng
             * ship_time : 1617355413
             * delivery_id : shunfeng_3617355413
             * company_name : 顺丰
             * product_info : [{"product_name":"苹果","price":1000,"outer_sku_id":"sdfa","sku_id":3254535,"sku_specs":[{"name":"颜色","value":"蓝"}],"product_count":2,"product_id":3473196049974326300,"sku_order_id":"4781320682406083640"}]
             */

            private String tracking_no;
            private String company;
            private long ship_time;
            private String delivery_id;
            private String company_name;
            private List<ProductInfoBean> product_info;

            @NoArgsConstructor
            @Data
            public static class ProductInfoBean {
                /**
                 * product_name : 苹果
                 * price : 1000
                 * outer_sku_id : sdfa
                 * sku_id : 3254535
                 * sku_specs : [{"name":"颜色","value":"蓝"}]
                 * product_count : 2
                 * product_id : 3473196049974326300
                 * sku_order_id : 4781320682406083640
                 */

                private String product_name;
                private int price;
                private String outer_sku_id;
                private int sku_id;
                private int product_count;
                private long product_id;
                private String sku_order_id;
                private List<SkuSpecsBean> sku_specs;

                @NoArgsConstructor
                @Data
                public static class SkuSpecsBean {
                    /**
                     * name : 颜色
                     * value : 蓝
                     */

                    private String name;
                    private String value;
                }
            }
        }

        @Data
        @NoArgsConstructor
        public static class SkuOrderListBean {
            /**
             * order_id : 4781320682406083640
             * parent_order_id : 4781320682406083640
             * order_level : 3
             * biz : 1
             * biz_desc : 鲁班
             * order_type : 0
             * order_type_desc : 普通订单
             * trade_type : 1
             * trade_type_desc : 拼团
             * order_status : 3
             * order_status_desc : 待支付
             * main_status : 103
             * main_status_desc : 部分支付
             * pay_time : 1617355413
             * order_expire_time : 1800
             * finish_time : 1617355413
             * create_time : 1617355413
             * update_time : 1617355413
             * cancel_reason : 不想要
             * b_type : 1
             * b_type_desc : 抖音
             * sub_b_type : 1
             * sub_b_type_desc : 小程序
             * send_pay : 1
             * send_pay_desc : 鲁班广告
             * author_id : 324234453
             * author_name : XXXX
             * theme_type : 1
             * theme_type_desc : 直播间
             * app_id : 43545454556
             * room_id : 43545454556
             * content_id : 43545454556
             * video_id : 43545454556
             * origin_id : 43545454556
             * cid : 43545454556
             * c_biz : 1
             * c_biz_desc : 鲁班广告
             * page_id : 43545454556
             * pay_type : 1
             * channel_payment_no : PAY234532534534
             * order_amount : 600
             * pay_amount : 500
             * post_insurance_amount : 10
             * modify_amount : -10
             * modify_post_amount : -1
             * promotion_amount : 1000
             * promotion_shop_amount : 100
             * promotion_platform_amount : 100
             * shop_cost_amount : 100
             * platform_cost_amount : 100
             * promotion_talent_amount : 100
             * promotion_pay_amount : 10
             * code : djfafj
             * post_tel : 12345678911
             * post_receiver : 张三
             * post_addr : {"province":{"name":"北京市","id":"110000"},"city":{"name":"市辖区","id":"110000"},"town":{"name":"海淀区","id":"110000"},"street":{"name":"中关村街道","id":"110000"},"detail":"丹棱街1号"}
             * exp_ship_time : 1617355413
             * ship_time : 1617355413
             * logistics_receipt_time : 1617355413
             * confirm_receipt_time : 1617355413
             * goods_type : 1
             * product_id : 3473196049974326300
             * sku_id : 3254535
             * spec : [{"name":"颜色","value":"蓝"}]
             * first_cid : 20005
             * second_cid : 20174
             * third_cid : 20174
             * fourth_cid : 20174
             * out_sku_id : 43564553
             * supplier_id : fsgfgsdg
             * out_product_id : 432655662343
             * warehouse_ids : ["49574835439","954764056"]
             * out_warehouse_ids : ["49574835439","954764056"]
             * inventory_type : 2
             * inventory_type_desc : 普通库存
             * reduce_stock_type : 1
             * reduce_stock_type_desc : 下单减库存
             * origin_amount : 100
             * has_tax : true
             * item_num : 2
             * sum_amount : 200
             * source_platform : XXX
             * product_pic : https:xxxxx
             * is_comment : 1
             * product_name : 衣服
             * inventory_list : [{"warehouse_id":"3141234234324","out_warehouse_id":"3254234234234","inventory_type":1,"inventory_type_desc":"普通库存","count":2}]
             * post_amount : 1
             * pre_sale_type : 1
             * after_sale_info : {"after_sale_status":6,"after_sale_type":1,"refund_status":1}
             */
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
            private long pay_time;
            private long order_expire_time;
            private long finish_time;
            private long create_time;
            private long update_time;
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
            private PostAddrBeanX post_addr;
            private long exp_ship_time;
            private long ship_time;
            private long logistics_receipt_time;
            private long confirm_receipt_time;
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

            @NoArgsConstructor
            @Data
            public static class PostAddrBeanX {
                /**
                 * province : {"name":"北京市","id":"110000"}
                 * city : {"name":"市辖区","id":"110000"}
                 * town : {"name":"海淀区","id":"110000"}
                 * street : {"name":"中关村街道","id":"110000"}
                 * detail : 丹棱街1号
                 */

                private ProvinceBeanX province;
                private CityBeanX city;
                private TownBeanX town;
                private StreetBeanX street;
                private String detail;

                @NoArgsConstructor
                @Data
                public static class ProvinceBeanX {
                    /**
                     * name : 北京市
                     * id : 110000
                     */

                    private String name;
                    private String id;
                }

                @NoArgsConstructor
                @Data
                public static class CityBeanX {
                    /**
                     * name : 市辖区
                     * id : 110000
                     */

                    private String name;
                    private String id;
                }

                @NoArgsConstructor
                @Data
                public static class TownBeanX {
                    /**
                     * name : 海淀区
                     * id : 110000
                     */

                    private String name;
                    private String id;
                }

                @NoArgsConstructor
                @Data
                public static class StreetBeanX {
                    /**
                     * name : 中关村街道
                     * id : 110000
                     */

                    private String name;
                    private String id;
                }
            }

            @NoArgsConstructor
            @Data
            public static class AfterSaleInfoBean {
                /**
                 * after_sale_status : 6
                 * after_sale_type : 1
                 * refund_status : 1
                 */

                private int after_sale_status;
                private int after_sale_type;
                private int refund_status;
            }

            @NoArgsConstructor
            @Data
            public static class SpecBean {
                /**
                 * name : 颜色
                 * value : 蓝
                 */

                private String name;
                private String value;
            }

            @NoArgsConstructor
            @Data
            public static class InventoryListBean {
                /**
                 * warehouse_id : 3141234234324
                 * out_warehouse_id : 3254234234234
                 * inventory_type : 1
                 * inventory_type_desc : 普通库存
                 * count : 2
                 */

                private String warehouse_id;
                private String out_warehouse_id;
                private int inventory_type;
                private String inventory_type_desc;
                private int count;
            }
        }

        @Data
        @NoArgsConstructor
        public static class OrderPhaseListBean {
            /**
             * phase_order_id : 4781320682406083640
             * total_phase : 2
             * current_phase : 1
             * pay_success : true
             * sku_order_id : 4781320682406083640
             * campaign_id : 3214324342342
             * phase_payable_price : 100
             * phase_pay_type : 1
             * phase_open_time : 1617355413
             * phase_pay_time : 1617355413
             * phase_close_time : 1617355413
             * channel_payment_no : PAY34243247134325
             * phase_order_amount : 100
             * phase_sum_amount : 100
             * phase_post_amount : 100
             * phase_pay_amount : 100
             * phase_promotion_amount : 100
             * current_phase_status_desc : 已开始但未支付
             * sku_price : 100
             */
            private String phase_order_id;
            private int total_phase;
            private int current_phase;
            private boolean pay_success;
            private String sku_order_id;
            private long campaign_id;
            private int phase_payable_price;
            private int phase_pay_type;
            private long phase_open_time;
            private long phase_pay_time;
            private long phase_close_time;
            private String channel_payment_no;
            private int phase_order_amount;
            private int phase_sum_amount;
            private int phase_post_amount;
            private int phase_pay_amount;
            private int phase_promotion_amount;
            private String current_phase_status_desc;
            private int sku_price;
        }
    }
}
