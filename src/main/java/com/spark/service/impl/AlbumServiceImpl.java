package com.spark.service.impl;

import com.spark.dao.AlbumDao;
import com.spark.model.Album;
import com.spark.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2017/6/10.
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    public Album save(Album album) {
        return albumDao.save(album);
    };

    public Album findByAlbumId(String albumId) {
        return albumDao.findByAlbumId(albumId);
    };
}
