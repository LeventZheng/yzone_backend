package com.spark.service.impl;

import com.spark.dao.VideoDao;
import com.spark.model.Video;
import com.spark.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2017/6/9.
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoDao videoDao;

    public Video save(Video video) {
        return videoDao.save(video);
    };
}
