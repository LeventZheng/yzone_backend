package com.spark.service.impl;

import com.spark.dao.MusicDao;
import com.spark.model.Music;
import com.spark.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicDao musicDao;

    public Iterable<Music> getAllMusic() {
        return musicDao.findAll();
    }
}
