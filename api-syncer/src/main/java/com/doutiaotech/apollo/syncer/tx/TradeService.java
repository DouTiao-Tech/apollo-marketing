package com.doutiaotech.apollo.syncer.tx;

import com.doutiaotech.apollo.core.utils.JsonUtils;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Transactional
public class TradeService {

    public static final String TRADE_TOPIC = "trade";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public long sendToKafka(List<TradeSearchPage.ShopOrderListBean> orderList) {
        orderList.stream()
                .map(order -> kafkaTemplate.send(TRADE_TOPIC, order.getOrder_id(), JsonUtils.toJson(order)))
                .collect(Collectors.toList())
                .forEach(Unchecked.consumer(Future::get));
        return orderList.size();
    }

}
