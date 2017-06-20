package com.spark.dao;

import com.spark.model.Music;
import com.spark.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
public interface MusicDao extends CrudRepository<Music, Long> {
    List<Music> findByUser(User user);
}
