package com.doutiao.apollo.sms;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.doutiao.apollo.sms.domain.Sms;
import com.doutiao.apollo.sms.sender.CompositeSender;
import com.doutiaotech.apollo.core.dao.mysql.SendRecordDao;
import com.doutiaotech.apollo.core.dao.mysql.SmsTemplateDao;
import com.doutiaotech.apollo.core.model.mysql.SendRecord;
import com.doutiaotech.apollo.core.model.mysql.SmsTemplate;
import com.google.common.collect.Streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SmsMsgListener {

    @Autowired
    private SendRecordDao sendRecordDao;

    @Autowired
    private SmsTemplateDao smsTemplateDao;

    @Autowired
    private CompositeSender compositeSender;

    @KafkaListener(topics = "trade", groupId = "sms-invoker")
    public void sendSms(List<Long> sendRecordIds) {
        Iterable<SendRecord> records = sendRecordDao.findAllById(sendRecordIds);
        batchSend(records);
    }

    private void batchSend(Iterable<SendRecord> records) {
        List<Sms> sms = generateSms(records);
        compositeSender.batchSend(sms);
    }

    private List<Sms> generateSms(Iterable<SendRecord> records) {
        List<Long> templateIds = Streams.stream(records).map(SendRecord::getTemplateId).collect(Collectors.toList());
        Iterable<SmsTemplate> templates = smsTemplateDao.findAllById(templateIds);
        Map<Long, SmsTemplate> idTemplateMap = Streams.stream(templates)
                .collect(Collectors.toMap(SmsTemplate::getId, Function.identity()));
        return Streams.stream(records).map(record -> new Sms(record, idTemplateMap.get(record.getTemplateId())))
                .collect(Collectors.toList());
    }

}
