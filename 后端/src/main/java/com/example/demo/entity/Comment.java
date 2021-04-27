package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private String cid;
    @Column(name = "video_id")
    private String vid;
    @Column(name = "user_id")
    private String uid;
    @Column(name = "comment_time")
    private Date time;
    @Column(name = "comment_content")
    private String content;
}
