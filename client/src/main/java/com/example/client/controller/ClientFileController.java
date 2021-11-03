package com.example.client.controller;

import com.example.client.util.ConnectionUtil;
import com.example.client.util.StreamUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 客户端实现
 *
 * @author stream
 * @since 2021/11/3 23:06
 */

public class ClientFileController {
    //服务器器接口访问地址
    private String ServerAddress = "http://127.0.0.1:8080/api/file/";

    //客户端文件存储位置
    private String LocalStorage = "D:\\data\\";

    /**
     * 上传文件
     * @param filepath 文件地址
     * @return  uuid
     * @throws IOException
     */
    public String UploadFile(String filepath) throws IOException {
        URL url = new URL(ServerAddress);
        File file = new File(filepath);
        HttpURLConnection connection = ConnectionUtil.Util(url);

        //设置访问方式
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        //在连接中设置文件信息
        String path = file.getAbsolutePath();
        String filetype = path.substring(path.indexOf(".")+1);
        connection.setRequestProperty("filename", file.getName());
        connection.setRequestProperty("filetype", filetype);
        connection.setRequestProperty("filesize", String.valueOf(file.length()));

        OutputStream out = null;
        FileInputStream in = null;
        try {
            out = new DataOutputStream(connection.getOutputStream());
            in = new FileInputStream(filepath);
            StreamUtil.OutUtil(in,out);
            in.close();
            out.flush();

        } catch (IOException e) {
//            log.info("传输异常！");
            return "传输异常";
        }

        //接受服务器返回的uuid
        InputStream  is = connection.getInputStream();
        String s = StreamUtil.InUtil(is);
        is.close();
        return s;
    }

    /**
     * 根据uuid下载服务器端文件
     * @param uuid 文件uuid
     * @return  成功信息
     * @throws IOException
     */
    public String DownloadFile(String uuid) throws IOException {
        URL url = new URL(ServerAddress);
        HttpURLConnection connection = ConnectionUtil.Util(url);
        connection.setRequestProperty("uuid",uuid);
        //设置连接访问方式
        connection.setRequestMethod("GET");
        //开启连接
        connection.connect();
        //判断返回值，确认是否成功
        if (connection.getResponseCode()!=200){
//            System.out.println("未成功！"+connection.getResponseCode());
            return "未成功！"+connection.getResponseCode();
        }
        //创建接收文件目录与文件名
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);
        String filetype = connection.getHeaderField("filetype");
        String FileDirectory = LocalStorage+"\\"+time;
        String DownPath = FileDirectory+"\\"+uuid+"."+filetype;

        File newFile = new File(FileDirectory);
        if (!newFile.exists()){
            newFile.mkdirs();
        }

        InputStream in = connection.getInputStream();
        OutputStream out = new FileOutputStream(DownPath);
        StreamUtil.OutUtil(in,out);
        in.close();
        out.close();

        return "成功下载文件！存储在："+DownPath;
    }

    /**
     * 获取文件元数据
     * @param uuid uuid
     * @return JSON格式元数据
     * @throws IOException
     */
    public JSONObject FileData(String uuid) throws IOException {
        URL url = new URL(ServerAddress+"metadata?uuid="+uuid);
        HttpURLConnection connection = ConnectionUtil.Util(url);
        InputStream in = connection.getInputStream();

        String s = StreamUtil.InUtil(in);
        in.close();

        //转为JSON格式输出
        return JSON.parseObject(s);
    }

}
