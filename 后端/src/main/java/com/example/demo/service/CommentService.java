package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.tool.Util;
import com.example.demo.vobject.Vcomment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Vcomment> getComments(String vid){
        List<Comment> all = commentRepository.findAll();
        List<Vcomment> vcomments = new ArrayList<>();

        int i = 1;
        for(Comment c : all){
            if(c.getVid().equals(vid)){
                String uname = UserService.getUname(c.getUid());
                String head = UserService.getHead(c.getUid());
                String time = Util.getPublishTime(c.getTime());
                String content = c.getContent();

                vcomments.add(new Vcomment(i++, uname, head, time, content));
            }
        }

        return vcomments;
    }

    public void makeAComment(String vid, String uid, String content){
        Comment comment = new Comment();
        String cid = vid+Util.getID(uid);
        comment.setCid(cid);
        comment.setUid(uid);
        comment.setVid(vid);
        comment.setContent(content);
        comment.setTime(new Date());
        commentRepository.save(comment);
    }

}
