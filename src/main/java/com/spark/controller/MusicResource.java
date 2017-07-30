package com.spark.controller;

import com.spark.common.controller.BaseController;
import com.spark.common.model.ResponseInfo;
import com.spark.model.Music;
import com.spark.model.User;
import com.spark.service.MusicService;
import com.spark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
@RestController
@RequestMapping("/rest/music")
public class MusicResource extends BaseController {

    @Autowired
    MusicService musicService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getMusic", method = RequestMethod.GET)
    public  ResponseInfo<Page<Music>> getAllMusic(
            @RequestParam(value = "pageNumber") int pageNumber, // 首页为0
            @RequestParam(value = "pageSize") int pageSize
            ) {
        ResponseInfo<Page<Music>> responseInfo = buildSuccessRetunInfo();
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto", "music");
        Page<Music> musicList = musicService.findAll(pageRequest);
        responseInfo.setData(musicList);
        return responseInfo;
    }

    @RequestMapping(value = "music/getMusicByUser", method = RequestMethod.GET)
    public  ResponseInfo<Page<Music>> getMusicByUser(
            @RequestParam(value = "pageNumber") int pageNumber, // 首页为0
            @RequestParam(value = "pageSize") int pageSize
    ) {
        ResponseInfo<Page<Music>> responseInfo = buildSuccessRetunInfo();
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto", "music");
        User user = userService.findByEmail("admin@yzone.com");
        Page<Music> musicList = musicService.findByUser(user, pageRequest);
        responseInfo.setData(musicList);
        return responseInfo;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseInfo<Boolean> save(@RequestBody Music music) {
        ResponseInfo<Boolean> responseInfo = buildSuccessRetunInfo();
        Music music1 = musicService.save(music);
        responseInfo.setData(true);
        return responseInfo;
    }

}
