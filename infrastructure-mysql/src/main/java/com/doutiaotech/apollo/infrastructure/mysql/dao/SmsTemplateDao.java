package com.doutiaotech.apollo.infrastructure.mysql.dao;

import com.doutiaotech.apollo.infrastructure.mysql.model.SmsTemplate;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmsTemplateDao extends PagingAndSortingRepository<SmsTemplate, Long> {
}
