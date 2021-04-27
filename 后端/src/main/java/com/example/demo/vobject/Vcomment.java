package com.example.demo.vobject;

import com.example.demo.entity.Comment;
import lombok.Data;

@Data
public class Vcomment {
    public Vcomment(int i, String uname, String head, String createDate, String content){
        id = String.valueOf(i);
        commentUser = new CommentUser(uname, head);
        this.createDate = createDate;
        this.content = content;
    }

    private String id,createDate,content;
    private CommentUser commentUser;
}

@Data
class CommentUser{
    public CommentUser(String uname, String head){
        id = "2";
        nickName = uname;
        avatar = head;
    }

    private String id, nickName, avatar;
}
