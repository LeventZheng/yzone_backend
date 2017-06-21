package com.spark.service.impl;

import com.spark.dao.VideoDao;
import com.spark.model.User;
import com.spark.model.Video;
import com.spark.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<Video> findByUser(User user, Pageable pageRequest) {
        return videoDao.findByUser(user, pageRequest);
    };
}
