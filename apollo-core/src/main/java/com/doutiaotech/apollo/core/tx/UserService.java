package com.doutiaotech.apollo.core.tx;

import com.doutiaotech.apollo.core.dao.mysql.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public void doSomething() {
        userDao.findById(0L);
        // do something
    }

}
