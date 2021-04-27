package com.example.demo.service;

import com.example.demo.tool.Util;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class AssetService {

    public byte[] getImage(String user, String name) throws IOException {
        File file = new File(Util.getImagePath(user,name));
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    public byte[] getVideo(String user, String name) throws IOException {
        File file = new File(Util.getVideoPath(user,name));
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

}
