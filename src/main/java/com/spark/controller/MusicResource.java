package com.spark.controller;

import com.spark.model.Music;
import com.spark.model.User;
import com.spark.service.MusicService;
import com.spark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
@RestController
@RequestMapping("/rest")
public class MusicResource {

    @Autowired
    MusicService musicService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "music/getMusicByUser", method = RequestMethod.POST)
    public List getAllMusicByUser() {

        User user = userService.findByEmail("admin@yzone.com");
        List<Music> musicList = musicService.findByUser(user);
        return musicList;
    }

    @RequestMapping(value = "music/save", method = RequestMethod.POST)
    public Music save(@RequestBody Music music) {
        Music music1 = musicService.save(music);
        return music1;
    }

}
