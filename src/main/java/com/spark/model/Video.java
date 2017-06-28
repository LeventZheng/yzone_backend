package com.spark.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2017/6/8.
 */
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoId;       // id
    private String fileUrl;    // 文件名
    private String videoTitle;   // 视频名

    @OneToOne(mappedBy="video")
    private Album album;
    // 所属用户
    @ManyToOne
    @JsonIgnore
    private User user;

    @CreationTimestamp
    private Date updatDate;       // 更新日期

    @CreationTimestamp
    private Date creatDate;       // 创建日期

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
