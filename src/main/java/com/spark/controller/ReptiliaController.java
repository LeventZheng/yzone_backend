package com.spark.controller;

import com.spark.model.Album;
import com.spark.model.Photo;
import com.spark.model.User;
import com.spark.service.ReptiliaService;
import com.spark.service.UserService;
import com.spark.service.YrangeService;
import com.spark.util.FileUtil;
import com.spark.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by User on 2017/6/9.
 */
@RestController
@RequestMapping("/reptilia")
public class ReptiliaController {

    @Autowired
    ReptiliaService reptiliaService;

    @Autowired
    YrangeService yrangeService;

    @GetMapping(value = "/xiumei")
    public List<Album> getResource(@RequestParam(value = "begin", required = true) Long beginY,
                            @RequestParam( value = "end", required = false) Long endY) {
        if (endY == null) {
            endY = beginY;
        }
        if (endY < beginY) {
            System.out.println("参数异常");
            return null;
        }
        List<Album> albumList = new ArrayList<Album>();
        for (Long y = beginY; y < endY; y++) {
            JSONObject jsonobject = reptiliaService.getResourceFromXiumei(y.toString());
            Album album = reptiliaService.save(jsonobject, y);
            albumList.add(album);
        }
        System.out.println("本次循环结束：" + endY);
        return albumList;
    }

    @PostMapping(value = "/local/pic")
    public void getLocalSrouce() {
        File[] tempList = FileUtil.getFileList("F:\\localPic");
        Long recordY = new Long(0);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {
                String y = tempList[i].getName();
                recordY = new Long(y);
                JSONObject jsonobject = reptiliaService.getResourceFromXiumei(y);
                Album album = reptiliaService.save(jsonobject, new Long(y));
            }
        }
        if (recordY > 0) {
            yrangeService.update(recordY);
        }
    }
}
