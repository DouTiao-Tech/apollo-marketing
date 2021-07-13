package com.doutiaotech.apollo.external.dy.request;

import com.doutiaotech.apollo.core.utils.JsonUtils;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class CommonRequest<T> {

    /**
     * 必填： 调用的API接口名称
     * 示例： /order/searchList
     */
    private String method;

    /**
     * 必填： 应用创建完成后被分配的key
     * 示例： 6839996111118329223
     */
    private String app_key;

    /**
     * 必填： 用于调用 API 的 access_token
     * 示例： edae7c30-8386-443b-88a1-031111596fdd
     */
    private String access_token;

    /**
     * 必填： 标准json类型，里面是业务参数按照参数名的字符串大小排序具体参数值，参考每个接口的参数表
     * 示例： {cid:12,page:1}
     */
    private String param_json = "{}";

    /**
     * 必填： 时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2016-01-01 12:00:00
     * 示例： 2020-09-15 14:48:13
     */
    private String timestamp;

    /**
     * 必填： API协议版本，当前版本为2
     * 示例： 2
     */
    private String v;

    /**
     * 必填： 输入参数签名结果，签名算法参照 [API调用指南](https://op.jinritemai.com/docs/guide-docs/10/23)
     * 示例： 796559d40beb08a1a1113c456c5c5a62
     */
    private String sign;

    @SneakyThrows
    public void setParamObj(T obj) {
        param_json = JsonUtils.toJson(obj);
    }

}
