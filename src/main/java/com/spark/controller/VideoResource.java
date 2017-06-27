package com.spark.controller;

import com.spark.model.Music;
import com.spark.model.User;
import com.spark.model.Video;
import com.spark.service.UserService;
import com.spark.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 2017/6/21.
 */
@RestController
@RequestMapping(value = "/rest")
public class VideoResource {

    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "/video/getVideoByUser",method = RequestMethod.GET)
    public Page<Video> getAllVideoByUser(
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        User user = userService.findByEmail("admin@yzone.com");

//        int pageNumber = new Long(json.get("pageNumber")).intValue();
//        int pageSize = new Long(json.get("pageSize")).intValue();
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
        Page<Video> videoList = videoService.findByUser(user, pageRequest);

        return videoList;
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "videoId");
        } else if ("title".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "videoTitle");
        }

        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }
}
