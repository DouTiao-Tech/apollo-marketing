package com.doutiaotech.apollo.external.dy.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * https://op.jinritemai.com/docs/api-docs/15/75
 */
@FeignClient(value = "order", url = "https://openapi-fxg.jinritemai.com/")
public interface OrderApi {

    @GetMapping("/order/searchList")
    void searchList();

}
