package com.spark.service;

import com.spark.model.Yrange;

/**
 * Created by User on 2017/6/11.
 */
public interface YrangeService {
    Yrange save(Yrange yrange);

    Yrange update(Long y);
}
