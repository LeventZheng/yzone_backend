package com.spark.dao;

import com.spark.model.User;
import com.spark.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by User on 2017/6/9.
 */
public interface VideoDao extends PagingAndSortingRepository<Video, Long> {

    Video save(Video video);

    Page<Video> findByUser(User user, Pageable pageRequest);
}
