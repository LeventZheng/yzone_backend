package com.spark.dao;

import com.spark.model.Music;
import com.spark.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
public interface MusicDao extends CrudRepository<Music, Long> {
    Page<Music> findAll(Pageable pageRequest);
    Page<Music> findByUser(User user, Pageable pageRequest);
    Music findByFileUrl(String musicUrl);
    Music save(Music music);
}
