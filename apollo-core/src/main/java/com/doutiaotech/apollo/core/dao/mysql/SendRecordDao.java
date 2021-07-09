package com.doutiaotech.apollo.core.dao.mysql;

import com.doutiaotech.apollo.core.model.mysql.SendRecord;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SendRecordDao extends PagingAndSortingRepository<SendRecord, Long> {
}
