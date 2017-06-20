package com.spark.service;

import com.spark.model.Music;
import com.spark.model.User;

import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
public interface MusicService {
    List<Music> findByUser(User user);
}
