package com.spark.service.impl;

import com.spark.dao.YrangeDao;
import com.spark.model.Yrange;
import com.spark.service.YrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2017/6/11.
 */
@Service
public class YrangeServiceImpl implements YrangeService {

    @Autowired
    YrangeDao yrangeDao;

    public Yrange save(Yrange yrange) {
        return yrangeDao.save(yrange);
    };

    public Yrange update(Long y) {
        Yrange yrange = yrangeDao.findOne(new Long(1));
        if (yrange == null) {
            yrange = new Yrange();
        }
        yrange.setY(y);
        return yrangeDao.save(yrange);
    };
}
