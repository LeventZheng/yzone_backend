package com.spark.service.impl;

import com.spark.dao.MusicDao;
import com.spark.model.Music;
import com.spark.model.User;
import com.spark.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicDao musicDao;

    public Page<Music> findAll(Pageable pageRequest) {
        return musicDao.findAll(pageRequest);
    };

    public Page<Music> findByUser(User user, PageRequest pageRequest) {
        return musicDao.findByUser(user, pageRequest);
    };

    public Music save(Music music) {
        return musicDao.save(music);
    };
}
