package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "collect")
public class Collection {
    @Id
    @Column(name = "collect_id")
    private String cid;
    @Column(name = "user_id")
    private String uid;
    @Column(name = "video_id")
    private String vid;
}
