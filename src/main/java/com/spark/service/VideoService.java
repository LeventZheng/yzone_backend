package com.spark.service;

import com.spark.model.User;
import com.spark.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by User on 2017/6/9.
 */
public interface VideoService {
    Video save(Video video);

    Video findByVideoId(Long videoId);

    Page<Video> findByUser(User user, Pageable pageRequest);
}
