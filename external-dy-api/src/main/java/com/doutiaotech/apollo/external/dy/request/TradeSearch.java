package com.doutiaotech.apollo.external.dy.request;

import lombok.Data;

import java.util.List;

@Data
public class TradeSearch {

    /**
     * 描述：商品，number型代表商品ID，其它代表商品名称
     * 必填：否
     * 示例：3473196049974326153
     */
    private String product;
    /**
     * 描述：app渠道:0 站外 1 火山 2 抖音 3 头条 4 西瓜 5 微信 6 值点app 7 头条lite 8 懂车帝 9 皮皮虾 11 抖音极速版 12 TikTok 13 musically 14 穿山甲 15 火山极速版 16 服务市场
     * 必填：否
     * 示例：2
     */
    private Number b_type;
    /**
     * 描述：售后状态：all-全部，in_aftersale-售后中，refund-退款中，refund_success-退款成功，refund_fail-退款失败，exchange_success-换货成功 aftersale_close-售后关闭
     * 必填：否
     * 示例：refund_success
     */
    private String after_sale_status_desc;
    /**
     * 描述：物流单号
     * 必填：否
     * 示例：435435
     */
    private String tracking_no;
    /**
     * 描述：预售类型：1 全款预售
     * 必填：否
     * 示例：1
     */
    private Number presell_type;
    /**
     * 描述：订类型:0-普通订单 2-虚拟订单 4-平台券码 5-商家券码
     * 必填：否
     * 示例：1
     */
    private Number order_type;
    /**
     * 描述：下单时间：开始
     * 必填：否
     * 示例：1617355413
     */
    private Number create_time_start;
    /**
     * 描述：下单时间：截止
     * 必填：否
     * 示例：1617355413
     */
    private Number create_time_end;
    /**
     * 描述：异常订单，1-异常取消，2-风控审核中
     * 必填：否
     * 示例：1
     */
    private Number abnormal_order;
    /**
     * 描述：交易类型，1 拼团订单，2 定金预售
     * 必填：否
     * 示例：1
     */
    private Number trade_type;
    /**
     * 描述：状态组合查询，直接输入状态码（只支持一个元素）
     * 必填：否
     */
    private List<Status> combine_status;

    public static class Status {
        /**
         * 描述：订单状态，","分隔多个状态
         * 必填：否
         * 示例：1
         */
        private String order_status;
        /**
         * 描述：主流程状态，","分隔多个状态
         * 必填：否
         * 示例：103
         */
        private String main_status;
    }

    /**
     * 描述：更新时间：开始
     * 必填：否
     * 示例：1617355413
     */
    private Number update_time_start;
    /**
     * 描述：更新时间：截止
     * 必填：否
     * 示例：1617355413
     */
    private Number update_time_end;
    /**
     * 描述：单页大小，限制100以内
     * 必填：是
     * 示例：20
     */
    private Number size;
    /**
     * 描述：页码，0页开始
     * 必填：是
     * 示例：0
     */
    private Number page;
    /**
     * 描述：排序条件(create_time 订单创建时间；update_time 订单更新时间；默认create_time；)
     * 必填：否
     * 示例：create_time
     */
    private String order_by;
    /**
     * 描述：排序类型，小到大或大到小，默认大到小
     * 必填：否
     * 示例：false
     */
    private Boolean order_asc;


}
