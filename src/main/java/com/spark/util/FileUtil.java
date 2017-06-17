package com.spark.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 2017/6/10.
 */
public class FileUtil {
    public static String guessContentTypeFromUrl(String strUrl) {

        BufferedInputStream bis = null;
        HttpURLConnection urlconnection = null;
        URL url = null;
        String contentType = "";
        try {
            url = new URL(strUrl);
            urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.connect();
            bis = new BufferedInputStream(urlconnection.getInputStream());
            System.out.println("file type:"+HttpURLConnection.guessContentTypeFromStream(bis));
            contentType = HttpURLConnection.guessContentTypeFromStream(bis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentType;
    }

    public static File[] getFileList(String path) {
        File file=new File(path);
        return file.listFiles();
//        File[] tempList = file.listFiles();
//        System.out.println("该目录下对象个数："+tempList.length);
//        for (int i = 0; i < tempList.length; i++) {
//            if (tempList[i].isFile()) {
//                System.out.println("文     件："+tempList[i]);
//            }
//            if (tempList[i].isDirectory()) {
//                System.out.println("文件夹："+tempList[i]);
//                System.out.println(tempList[i].getName());
//            }
//        }
    }
}
