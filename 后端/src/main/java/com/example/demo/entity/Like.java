package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "like_id")
    private String lid;
    @Column(name = "video_id")
    private String vid;
    @Column(name = "user_id")
    private String uid;
    @Column(name = "like_time")
    private Date time;
}
