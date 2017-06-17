package com.spark.dao;

import com.spark.model.Yrange;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User on 2017/6/11.
 */
public interface YrangeDao extends CrudRepository<Yrange, Long> {
    Yrange save(Yrange yrange);
}
