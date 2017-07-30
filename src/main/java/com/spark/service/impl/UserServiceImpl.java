package com.spark.service.impl;

import com.spark.dao.UserDao;
import com.spark.model.User;
import com.spark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2017/6/9.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User save(User user) {
        return userDao.save(user);
    };

    public User findOne(Long userId) {
        return userDao.findOne(userId);
    };

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    };

    public Iterable<User> findAll() {
        return userDao.findAll();
    };
}
