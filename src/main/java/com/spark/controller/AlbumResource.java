package com.spark.controller;

import com.spark.model.Album;
import com.spark.model.User;
import com.spark.service.AlbumService;
import com.spark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 2017/6/23.
 */
@RestController
@RequestMapping(value = "/rest")
public class AlbumResource {

    @Autowired
    UserService userService;
    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/video/user")
    public Page<Album> getVidelByUser(
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        User user = userService.findByEmail("admin@yzone.com");
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
        Page<Album> albumPage = albumService.findByUser(user, pageRequest);
        return albumPage;
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
