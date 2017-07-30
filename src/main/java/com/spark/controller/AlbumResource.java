package com.spark.controller;

import com.spark.common.controller.BaseController;
import com.spark.common.model.ResponseInfo;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017/6/23.
 */
@RestController
@RequestMapping(value = "/rest/album")
public class AlbumResource extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/getAlbumByUser")
    public ResponseInfo<Page<Album>> getVidelByUser(
            @RequestParam(value = "pageNumber") int pageNumber, // 首页为0
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "userId") Long userId
    ) {
        ResponseInfo<Page<Album>> responseInfo = buildSuccessRetunInfo();
        User user = userService.findOne(userId);
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto", "album");
        Page<Album> albumPage = albumService.findByUser(user, pageRequest);
        responseInfo.setData(albumPage);
        return responseInfo;
    }
}
