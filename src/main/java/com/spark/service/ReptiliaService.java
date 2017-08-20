package com.spark.service;

import com.spark.model.Album;
import com.spark.model.Photo;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by User on 2017/6/11.
 */
public interface ReptiliaService {
    JSONObject getResourceFromXiumei(String y);
    Album save(Long userId, JSONObject jsonobject, Long y);
}
