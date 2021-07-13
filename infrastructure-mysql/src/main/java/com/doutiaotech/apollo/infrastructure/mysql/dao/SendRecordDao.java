package com.doutiaotech.apollo.infrastructure.mysql.dao;

import com.doutiaotech.apollo.infrastructure.mysql.model.SendRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SendRecordDao extends PagingAndSortingRepository<SendRecord, Long> {
}
