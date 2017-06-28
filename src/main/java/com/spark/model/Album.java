package com.spark.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2017/6/8.
 */
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long albumId;         // id
    private String albumTitle;   // 相册名
    private Long resourceType;   //  1代表图片，2代表视频

    private Long y;         // y值

    // 所属用户
    @ManyToOne
    @JsonIgnore
    private User user;

    // 配的音乐，允许多个相册配同一首音乐
    @ManyToOne
    @JsonBackReference
    private Music music;

    // 相册和相片属于多对多的关系
    @ManyToMany
    private List<Photo> photoList;


    // 相册和视频之间属于一对一的关系
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="VIDEO_VIDEO_ID")
    private Video video;

    // 点赞人数
    private int likes;

    // 评论
    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Comment> commentList;

    @CreationTimestamp
    private Date updatDate;       // 更新日期

    @CreationTimestamp
    private Date creatDate;       // 创建日期

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Long getResourceType() {
        return resourceType;
    }

    public void setResourceType(Long resourceType) {
        this.resourceType = resourceType;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

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
