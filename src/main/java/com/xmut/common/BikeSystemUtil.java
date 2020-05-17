package com.xmut.common;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BikeSystemUtil {
    /**
     * 获取图片的Base64编码
     * @param imageFile
     * @return
     */
    public static String getImageBase64(String imageFile){
        InputStream in = null;
        byte[] data = null;
        // 读取图片
        try{
            in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 计算骑行时间
     * @param seconds
     * @return
     */
    public static String getTripDistance(Long seconds) {
        Long second = seconds % 60;
        Long minutes = (seconds - second) / 60;
        Long mintue = minutes % 60;
        Long hour = (minutes - mintue) / 60;
        StringBuffer tripTimeDesc = new StringBuffer();
        if (0 != hour) {
            tripTimeDesc.append(hour);
            tripTimeDesc.append("小时");
        }
        if (0 != mintue) {
            tripTimeDesc.append(mintue);
            tripTimeDesc.append("分钟");
        }
        if (0 != second) {
            tripTimeDesc.append(second);
            tripTimeDesc.append("秒");
        }
        return tripTimeDesc.toString();
    }
}
