package com.spark.controller;

import com.spark.model.Music;
import com.spark.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/6/19.
 */
@RestController
@RequestMapping("/music")
public class MusicResource {

    @Autowired
    MusicService musicService;

    @RequestMapping(value = "all", method = RequestMethod.POST)
    public List getAllMusic() {
        Iterable<Music> musics = musicService.getAllMusic();
        List musicList = new ArrayList();

        return musicList;
    }

}
