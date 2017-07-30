package com.spark.service;

import com.spark.model.Music;
import com.spark.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
public interface MusicService {
    Page<Music> findAll(Pageable pageRequest);
    Page<Music> findByUser(User user, PageRequest pageRequest);
    Music save(Music music);
}
