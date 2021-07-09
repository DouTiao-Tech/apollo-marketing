package com.doutiaotech.apollo.core.dao.mysql;

import com.doutiaotech.apollo.core.model.mysql.SmsTemplate;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmsTemplateDao extends PagingAndSortingRepository<SmsTemplate, Long> {
}
