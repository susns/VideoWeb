package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.Video;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import com.example.demo.tool.Util;
import com.example.demo.vobject.Exhibition;
import com.example.demo.vobject.Recommend;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService{
    private static UserRepository userRepository;

    private int getRandomAccountNumber(){
        return (int)((Math.random()*9+1)*100000);
    }

    public static String getUname(String uid){
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        for(User u : users){
            if(u.getUid().equals(uid))
                return u.getName();
        }

        return "";
    }

    public static String getHead(String uid){
        List<User> users = userRepository.findAll();
        for(User u : users){
            if(u.getUid().equals(uid))
                return Util.getImageUrl(uid, u.getHead());
        }

        return "";
    }

    private void setDefaultHead(String uid) {
        File result = new File(Util.getImagePath(uid, "head.jpg"));
        if (!result.getParentFile().exists()) {
            result.getParentFile().mkdirs();
        }

        FileOutputStream out;
        try (FileInputStream input = new FileInputStream("./image/default.jpg")) {
            out = new FileOutputStream(result);
            byte[] bytes = new byte[input.available()];
            input.read(bytes, 0, input.available());
            out.write(bytes);

            input.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Autowired
    public UserService(UserRepository u){
        userRepository = u;
    }

    public String register(String nickname, String password) {
        List<User> users = new ArrayList<User>();
        users = userRepository.findAll();

        String accountNumber = String.valueOf(getRandomAccountNumber());
        boolean tag = true;
        while(tag) {
            tag = false;
            for (User u : users) {
                if (u.getUid().equals(accountNumber)) {
                    tag = true;
                    break;
                }
            }
        }
        User u = new User();
        u.setUid(accountNumber);
        u.setPassword(password);
        u.setName(nickname);
        setDefaultHead(accountNumber);
        u.setHead("head.jpg");
        userRepository.save(u);

        JSONObject result = new JSONObject();
        try {
            result.put("uid", accountNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String login(String accountNumber, String password) {
        JSONObject result = new JSONObject();
        List<User> users = new ArrayList<User>();
        users = userRepository.findAll();

        for (User u : users) {
            if (u.getUid().equals(accountNumber) && u.getPassword().equals(password)) {
                try {
                    result.put("result", "1");
                    result.put("uname", u.getName());
                    String head = Util.getImagePath(u.getUid(),u.getHead());
                    result.put("head", head);
                    return result.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            result.put("result", "0");
            result.put("uname", "");
            result.put("head", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String getUser(String uid){
        JSONObject result = new JSONObject();
        List<User> users = new ArrayList<User>();
        users = userRepository.findAll();

        for (User u : users) {
            if (u.getUid().equals(uid)) {
                try {
                    result.put("uname", u.getName());
                    String head = Util.getImageUrl(u.getUid(),u.getHead());
                    result.put("head", head);
                    return result.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            result.put("uname", "");
            result.put("head", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
