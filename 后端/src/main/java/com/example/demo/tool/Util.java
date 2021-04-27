package com.example.demo.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static String path = "http://localhost:8081";

    public static String getPublishTime(Date time){
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(time);
    }

    public static String getDuration(Date time){
        return new SimpleDateFormat("HH:mm:ss").format(time);
    }

    public static String getID(String uid){
        String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return uid+id;
    }

    public static String getVideoUrl(String uid, String vid){
        return path+"/download/video/"+uid+"/"+vid;
    }

    public static String getVideoPath(String uid, String vid){
        return "./video/"+uid+"/"+vid;
    }

    public static String getImageUrl(String uid, String image){
        return path+"/download/image/"+uid+"/"+image;
    }

    public static String getImagePath(String uid, String image){
        return "./image/"+uid+"/"+image;
    }
}
