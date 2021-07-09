package com.doutiaotech.apollo.infrastructure.mysql.dao;

import com.doutiaotech.apollo.infrastructure.mysql.model.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
}
