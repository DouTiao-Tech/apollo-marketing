package com.doutiaotech.apollo.external.dy.api;

import com.doutiaotech.apollo.external.dy.request.CommonRequest;
import com.doutiaotech.apollo.external.dy.request.TradeSearch;
import com.doutiaotech.apollo.external.dy.response.Response;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * https://op.jinritemai.com/docs/api-docs/15/75
 */
@FeignClient(value = "order", url = "https://openapi-fxg.jinritemai.com/")
public interface OrderApi {

    @GetMapping("/order/searchList")
    Response<TradeSearchPage> searchList(CommonRequest<TradeSearch> request);

}
