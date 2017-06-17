package com.spark.dao;

import com.spark.model.Video;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User on 2017/6/9.
 */
public interface VideoDao extends CrudRepository<Video, Long> {

    Video save(Video video);
}
