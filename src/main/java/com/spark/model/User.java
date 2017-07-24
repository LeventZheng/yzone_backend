package com.spark.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 2017/6/8.
 */
@Entity
public class User {
    // id对应表格中的key，GeneratedValue(strategy = GenerationType.AUTO)代表自增
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;         // 用户id
    private String userNickName;     // 昵称
    private String email;        // 邮箱地址
    private String password;    // 密码
    private String avatar;      // 用户头像

    // 和相片一对多
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Photo> photoList;

    // 和音乐一对多
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Music> musicList;

    // 和视频一对多
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Video> videoList;

    // 和相册一对多
    // 一对多的映射关系,一个用户可以有多个相册，一个相册只属于一个用户
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Album> albumList;

    // 多对多的关系，用户可以喜欢多个相册，相册也可以被多个用户喜欢
    @ManyToMany
    private List<Album> likedAlbumList;

    @CreationTimestamp
    private Date updatDate;       // 更新日期

    @CreationTimestamp
    private Date creatDate;       // 创建日期

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

//    public Set<Photo> getPhotoList() {
//        return photoList;
//    }
//
//    public void setPhotoList(Set<Photo> photoList) {
//        this.photoList = photoList;
//    }

    public Set<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(Set<Music> musicList) {
        this.musicList = musicList;
    }

    /*public Set<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(Set<Video> videoList) {
        this.videoList = videoList;
    }

    public Set<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(Set<Album> albumList) {
        this.albumList = albumList;
    }

    public List<Album> getLikedAlbumList() {
        return likedAlbumList;
    }

    public void setLikedAlbumList(List<Album> likedAlbumList) {
        this.likedAlbumList = likedAlbumList;
    }*/

    public Date getUpdatDate() {
        return updatDate;
    }

    public void setUpdatDate(Date updatDate) {
        this.updatDate = updatDate;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }
}
