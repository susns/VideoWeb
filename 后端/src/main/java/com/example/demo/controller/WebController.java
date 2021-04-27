package com.example.demo.controller;

import com.example.demo.service.AssetService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import com.example.demo.service.VideoService;
import com.example.demo.tool.Util;
import com.example.demo.vobject.Exhibition;
import com.example.demo.vobject.Recommend;
import com.example.demo.vobject.Vcomment;
import com.example.demo.vobject.Vvideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class WebController {
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AssetService assetService;


    @RequestMapping("/say.html")
    public String say() {
        return "hello spring boot.";
    }


    ////////////////////////////////////////////用户相关//////////////////////////////////////////////
    @RequestMapping("/register")
    public String register(@RequestParam(value = "uname") String nickname, @RequestParam(value = "password") String password){
        return userService.register(nickname, password);
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "uid") String accountNumber, @RequestParam(value = "password") String password){
        return userService.login(accountNumber, password);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{uid}", produces = "application/json;charset=UTF-8")
    public String getUserInformation(@PathVariable String uid) {
        return userService.getUser(uid);
    }

    ////////////////////////////////////////////视频相关//////////////////////////////////////////////
    @ResponseBody
    @RequestMapping(value = "/recommend")
    public List<Recommend> getRecommend() {
        return videoService.getRecommend();
    }

    @ResponseBody
    @RequestMapping(value = "/exhibit", produces = "application/json;charset=UTF-8")
    public List<Exhibition> getExhibition() {
        return videoService.getExhibition();
    }

    @ResponseBody
    @RequestMapping(value = "/myvideo/{uid}", produces = "application/json;charset=UTF-8")
    public List<Exhibition> getMyVideo(@PathVariable String uid) {
        return videoService.getMyVideo(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/mycollection/{uid}", produces = "application/json;charset=UTF-8")
    public List<Exhibition> getMyCollection(@PathVariable String uid) {
        return videoService.getMyCollection(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/video/{vid}", produces = "application/json;charset=UTF-8")
    public Vvideo getVideoInformation(@PathVariable String vid) {
        return videoService.getVideo(vid);
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{vid}", produces = "application/json;charset=UTF-8")
    public List<Vcomment> getComments(@PathVariable String vid) {
        return commentService.getComments(vid);
    }

    @ResponseBody
    @RequestMapping(value = "/make/comment", produces = "application/json;charset=UTF-8")
    public void getComments(@RequestParam(value = "uid") String uid, @RequestParam(value = "vid") String vid, @RequestParam(value = "content") String content) {
        commentService.makeAComment(uid, vid, content);
    }

    ////////////////////////////////////////////上传下载图片视频//////////////////////////////////////////////
    @RequestMapping(value = "/download/image/{user}/{name}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] downloadImage(@PathVariable String user, @PathVariable String name) throws IOException {
        return assetService.getImage(user, name);
    }

    @RequestMapping(value = "/download/video/{user}/{name}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] downloadVideo(@PathVariable String user, @PathVariable String name) throws IOException {
        return assetService.getVideo(user, name);
    }


    @RequestMapping(value = "/upload/image/{user}", produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean uploadImage(@PathVariable String user, @RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }

        // 获取当前时间作为文件名
        String fileName = Util.getID(user);
        // 获取文件的后缀名
        String suffixName = file.getName().substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = "./image/"+user+"/";
        File dest = new File(filePath + fileName+"."+suffixName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }




}
