package com.example.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "video")
public class Video {
    @Id
    @Column(name = "video_id")
    private String vid;
    @Column(name = "user_id")
    private String uid;
    @Column(name = "video_title")
    private String title;
    @Column(name = "video_cover")
    private String cover;
    @Column(name = "video_publish_time")
    private Date time;
    @Column(name = "video_type")
    private String type;
    @Column(name = "video_brief_introduction")
    private String introduction;
    @Column(name = "video_duration")
    private Date duration;
}
