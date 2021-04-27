package com.example.demo.vobject;

import lombok.Data;

@Data
public class Recommend {
    public Recommend(String vid, String cover){
        this.vid = vid;
        this.cover = cover;
    }
    private String vid, cover;
}
