package com.example.demo.vobject;

import lombok.Data;

@Data
public class Exhibition {
    public Exhibition(String vid, String uname, String title, String cover, String introduction, String ptime, String duration){
        this.vid = vid;
        this.uname = uname;
        this.title = title;
        this.cover = cover;
        this.introduction = introduction;
        this.ptime = ptime;
        this.duration = duration;
    }

    private String vid, uname, title, cover, introduction, ptime, duration;

}
