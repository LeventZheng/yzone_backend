package com.spark.dao;

import com.spark.model.Album;
import com.spark.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by User on 2017/6/10.
 */
public interface AlbumDao extends PagingAndSortingRepository<Album, Long> {
    Album save(Album album);

    Album findByAlbumId(Long albumId);

    Page<Album> findByUser(User user, Pageable pageRequest);
}
