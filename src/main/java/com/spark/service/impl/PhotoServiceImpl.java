package com.spark.service.impl;

import com.spark.dao.PhotoDao;
import com.spark.model.Photo;
import com.spark.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2017/6/10.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoDao photoDao;

    public Photo save(Photo photo){
        return photoDao.save(photo);
    };
}
