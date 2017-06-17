package com.spark.service;

import com.spark.model.Album;

/**
 * Created by User on 2017/6/10.
 */
public interface AlbumService {

    Album save(Album album);

    Album findByAlbumId(String albumId);
}
