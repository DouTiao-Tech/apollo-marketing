package com.doutiao.apollo.sms.sender;

import com.doutiao.apollo.sms.domain.Sms;

import java.util.List;

public interface Sender {

    void batchSms(List<Sms> sms);

}
