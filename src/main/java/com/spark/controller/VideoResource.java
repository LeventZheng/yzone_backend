package com.spark.controller;

import com.spark.common.controller.BaseController;
import com.spark.common.model.ResponseInfo;
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
@RequestMapping(value = "/rest/video")
public class VideoResource extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "/getVideoByUser",method = RequestMethod.GET)
    public ResponseInfo<Page<Video>> getVideoByUser(
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        User user = userService.findByEmail("admin@yzone.com");

        ResponseInfo<Page<Video>> responseInfo = buildSuccessRetunInfo();
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto", "video", Sort.Direction.DESC);
        Page<Video> videoList = videoService.findByUser(user, pageRequest);
        responseInfo.setData(videoList);
        return responseInfo;
    }
}
