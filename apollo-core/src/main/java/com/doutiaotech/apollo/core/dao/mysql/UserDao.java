package com.doutiaotech.apollo.core.dao.mysql;

import com.doutiaotech.apollo.core.model.mysql.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
}
