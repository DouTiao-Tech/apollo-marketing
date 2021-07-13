package com.doutiao.apollo.sms;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.doutiao.apollo.sms.domain.Sms;
import com.doutiao.apollo.sms.sender.CompositeSender;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SendRecordDao;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SmsTemplateDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.SendRecord;
import com.doutiaotech.apollo.infrastructure.mysql.model.SmsTemplate;
import com.google.common.collect.Streams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsMsgListener {

    @Autowired
    private SendRecordDao sendRecordDao;

    @Autowired
    private SmsTemplateDao smsTemplateDao;

    @Autowired
    private CompositeSender compositeSender;

    @KafkaListener(topics = "sms", groupId = "sms-invoker")
    public void sendSms(List<Long> sendRecordIds) {
        log.info("fetch sms record:{} size", sendRecordIds.size());
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
