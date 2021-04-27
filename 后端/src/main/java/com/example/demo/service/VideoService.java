package com.example.demo.service;

import com.example.demo.entity.Collection;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Video;
import com.example.demo.repository.CollectionRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.VideoRepository;
import com.example.demo.tool.Util;
import com.example.demo.vobject.Exhibition;
import com.example.demo.vobject.Recommend;
import com.example.demo.vobject.Vcomment;
import com.example.demo.vobject.Vvideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService{
    private static VideoRepository videoRepository;
    private static CollectionRepository collectionRepository;

    @Autowired
    public VideoService(VideoRepository v, CollectionRepository col){
        videoRepository = v;
        collectionRepository = col;
    }

    public List<Recommend> getRecommend() {
        List<Video> videos = new ArrayList<>();
        videos = videoRepository.findAll();
        List<Recommend> recommends = new ArrayList<>();

        for(int i = 0;i < videos.size() && i < 6; i++){
            String vid = videos.get(i).getVid();
            String cover = Util.getImageUrl(videos.get(i).getUid(),videos.get(i).getCover());
            recommends.add(new Recommend(vid , cover));
        }

        return recommends;
    }

    public List<Exhibition> getExhibition() {
        List<Video> videos = new ArrayList<>();
        videos = videoRepository.findAll();
        List<Exhibition> exhibitions = new ArrayList<>();

        for(int i = 6;i < videos.size() && i < 30; i++){
            String vid = videos.get(i).getVid();
            String title = videos.get(i).getTitle();
            String cover = Util.getImageUrl(videos.get(i).getUid(),videos.get(i).getCover());
            String introduction = videos.get(i).getIntroduction();
            String ptime = Util.getPublishTime(videos.get(i).getTime());
            String duration = Util.getDuration(videos.get(i).getDuration());

            String uid = videos.get(i).getUid();
            String uname = UserService.getUname(uid);

            exhibitions.add(new Exhibition(vid,uname,title,cover,introduction,ptime,duration));
        }

        return exhibitions;
    }

    public Vvideo getVideo(String vid){
        List<Video> videos = new ArrayList<>();
        videos = videoRepository.findAll();
        for(Video v : videos){
            if(v.getVid().equals(vid)) {
                String time = Util.getPublishTime(v.getTime());
                String videoUrl = Util.getVideoUrl(v.getUid(),v.getVid());
                String coverUrl = Util.getImageUrl(v.getUid(),v.getCover());
                return new Vvideo(v.getTitle(), time, videoUrl, coverUrl);
            }
        }
        return null;
    }

    public List<Exhibition> getMyVideo(String uid){
        List<Video> videos = new ArrayList<>();
        videos = videoRepository.findAll();
        List<Exhibition> myVideos = new ArrayList<>();

        for(Video v : videos){
            if(v.getUid().equals(uid)) {
                String vid = v.getVid();
                String title = v.getTitle();
                String cover = Util.getImageUrl(v.getUid(), v.getCover());
                String introduction = v.getIntroduction();
                String ptime = Util.getPublishTime(v.getTime());
                String duration = Util.getDuration(v.getDuration());
                String uname = UserService.getUname(uid);

                myVideos.add(new Exhibition(vid, uname, title, cover, introduction, ptime, duration));
            }
        }

        return myVideos;
    }

    private Exhibition getOneExhibition(String vid){
        List<Video> videos = videoRepository.findAll();

        for(Video v: videos){
            if(v.getVid().equals(vid)){
                String title = v.getTitle();
                String cover = Util.getImageUrl(v.getUid(), v.getCover());
                String introduction = v.getIntroduction();
                String ptime = Util.getPublishTime(v.getTime());
                String duration = Util.getDuration(v.getDuration());
                String uname = UserService.getUname(v.getUid());

                return new Exhibition(vid, uname, title, cover, introduction, ptime, duration);
            }
        }
        return null;
    }

    public List<Exhibition> getMyCollection(String uid){
        List<Collection> collections = collectionRepository.findAll();
        List<Exhibition> myCollection = new ArrayList<>();

        for(Collection c : collections){
            if(c.getUid().equals(uid)){
                myCollection.add(getOneExhibition(c.getVid()));
            }
        }

        return myCollection;
    }



}
