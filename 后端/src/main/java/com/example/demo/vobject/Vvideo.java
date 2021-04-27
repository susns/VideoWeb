package com.example.demo.vobject;

import lombok.Data;

@Data
public class Vvideo {

    public Vvideo(String title, String time, String videoUrl, String coverUrl){
        this.title = title;
        this.time = time;
        this.videoUrl = videoUrl;
        this.coverUrl = coverUrl;
    }

    private String title, time, videoUrl, coverUrl;

}
