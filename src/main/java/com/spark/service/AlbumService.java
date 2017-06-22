package com.spark.service;

import com.spark.model.Album;
import com.spark.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by User on 2017/6/10.
 */
public interface AlbumService {

    Album save(Album album);

    Album findByAlbumId(String albumId);

    Page<Album> findByUser(User user, Pageable pageRequest);
}
