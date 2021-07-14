package com.doutiaotech.apollo.sms.sender;

import com.doutiaotech.apollo.sms.domain.Sms;

import java.util.List;

public interface Sender {

    void batchSms(List<Sms> sms);

}
