package com.spark.service;

import com.spark.model.User;

/**
 * Created by User on 2017/6/9.
 */
public interface UserService {
    User save(User user);

    User findByEmail(String email);

    Iterable<User> findAll();
}
