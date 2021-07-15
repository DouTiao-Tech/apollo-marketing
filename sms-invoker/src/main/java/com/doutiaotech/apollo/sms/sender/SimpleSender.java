package com.doutiaotech.apollo.sms.sender;

import com.doutiaotech.apollo.sms.domain.Sms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SimpleSender implements Sender {

    @Override
    public void batchSms(List<Sms> sms) {
        log.info("send {} sms", sms.size());
    }
}
