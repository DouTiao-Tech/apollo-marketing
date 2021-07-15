package com.doutiaotech.apollo.sms.sender;

import com.doutiaotech.apollo.sms.domain.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompositeSender {

    @Autowired
    private List<Sender> senders;

    public void batchSend(List<Sms> sms) {
        selectSender().batchSms(sms);
    }

    private Sender selectSender() {
        // 分流
        return senders.get(0);
    }
}
