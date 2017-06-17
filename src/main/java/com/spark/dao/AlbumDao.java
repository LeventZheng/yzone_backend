package com.spark.dao;

import com.spark.model.Album;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User on 2017/6/10.
 */
public interface AlbumDao extends CrudRepository<Album, Long> {
    Album save(Album album);

    Album findByAlbumId(String albumId);
}
