package com.doutiao.apollo.sms;

import com.doutiao.apollo.sms.domain.Sms;
import com.doutiao.apollo.sms.sender.CompositeSender;
import com.doutiaotech.apollo.core.dao.mysql.SendRecordDao;
import com.doutiaotech.apollo.core.dao.mysql.SmsTemplateDao;
import com.doutiaotech.apollo.core.model.mysql.SendRecord;
import com.doutiaotech.apollo.core.model.mysql.SmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        List<SendRecord> sendRecords = sendRecordDao.findAllById(sendRecordIds);
        batchSend(sendRecords);
    }

    private void batchSend(List<SendRecord> sendRecords) {
        List<Sms> sms = generateSms(sendRecords);
        compositeSender.batchSend(sms);
    }

    private List<Sms> generateSms(List<SendRecord> sendRecords) {
        List<Long> templateIds = sendRecords.stream().map(SendRecord::getTemplateId).collect(Collectors.toList());
        List<SmsTemplate> templates = smsTemplateDao.findAllById(templateIds);
        Map<Long, SmsTemplate> idTemplateMap = templates.stream().collect(Collectors.toMap(SmsTemplate::getId, Function.identity()));
        return sendRecords.stream().map(record -> new Sms(record, idTemplateMap.get(record.getTemplateId()))).collect(Collectors.toList());
    }

}
