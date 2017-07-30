package com.spark.dao;

import com.spark.model.Photo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User on 2017/6/10.
 */
public interface PhotoDao extends CrudRepository<Photo, Long> {
    Photo save(Photo photo);

    void delete(Long photoId);
}
