package com.spark.dao;

import com.spark.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 2017/6/9.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User save(User user);

    User findByEmail(String email);
}
