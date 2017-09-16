package com.spark.service.impl;

import com.spark.model.*;
import com.spark.service.*;
import com.spark.util.FileUtil;
import com.spark.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2017/6/11.
 */
@Service
public class ReptiliaServiceImpl implements ReptiliaService {
    @Value("${xiumeiApiUrl}") private String xiumeiApiUrl;

    @Autowired
    UserService userService;

    @Autowired
    PhotoService photoService;

    @Autowired
    VideoService videoService;

    @Autowired
    AlbumService albumService;

    @Autowired
    MusicService musicService;

    @Autowired
    YrangeService yrangeService;

    public JSONObject getResourceFromXiumei(String y) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("y", y);
//        params.put("s", "gnH5Tfhttg-29R3CHwQDacYWadsi5sPs");
        String result = HttpUtil.http(xiumeiApiUrl, params);
        return JSONObject.fromObject(result);
    };

    @Transactional
    public Album save(Long userId, JSONObject jsonobject, Long y, String musicUrl) {
        List<Photo> photoList = new ArrayList<Photo>();
        User user = userService.findOne(userId);
        // 如果用户为空就新注册一个
        /*if (user ==null) {
            user = new User();
            user.setEmail("admin@yzone.com");
            user.setPassword("admin");
            user.setUserNickName("望天涯");
            userService.save(user);
            user = userService.findByEmail("admin@yzone.com");
        }*/
        Album album = new Album();

        try {
            Boolean r = jsonobject.getBoolean("r");
            if (r == true) {
                // 创建相册
                album.setUser(user);

                // 相册返回的是i，视频返回的是v
                if (jsonobject.containsKey("i")) {
                    JSONArray arrayI = jsonobject.getJSONArray("i");
                    //将json数组 转换成 List<PassPortForLendsEntity>泛型
                    List list = new ArrayList();
                    Photo photo = new Photo();
                    for (int i = 0; i < arrayI.size(); i++) {
                        JSONObject object = (JSONObject)arrayI.get(i);
                        photo.setUser(user);
                        photo.setFileUrl(object.getString("url"));
                        // 保存相片
                        photoService.save(photo);
                        photoList.add(photo);
                        photo = new Photo();
                    }
                    album.setPhotoList(photoList);
                    album.setResourceType(new Long(1));
                    if (musicUrl != null) {
                        // 保存音乐
                        //先判断音乐是否已存在
                        Music music = musicService.findByFileUrl(musicUrl);
                        if (music == null) {
                            music = new Music();
                            music.setFileUrl(musicUrl);
                            music.setUser(user);
                        }
                        Music savedMusic = musicService.save(music);
                        album.setMusic(savedMusic);
                    }
                } else if (jsonobject.containsKey("v")){
                    String videUrl = jsonobject.getString("v");
                    Video video = new Video();
                    video.setUser(user);
                    video.setFileUrl(videUrl);
                    videoService.save(video);
                    album.setVideo(video);
                    album.setResourceType(new Long(2));
                }
                album.setY(y);
                albumService.save(album);
                yrangeService.update(y);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return album;
    };
}
