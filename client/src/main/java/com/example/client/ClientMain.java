package com.example.client;

import com.alibaba.fastjson.JSONObject;
import com.example.client.base.ClientFileBase;
import com.example.client.util.Choosepath;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * 客户端启动方法
 *
 * @author stream
 * @since 2021/11/3 23:06
 */
public class ClientMain {
    public static void main(String[] args) throws IOException {
        ClientFileBase fileController = new ClientFileBase();
        //测试上传文件
        String upFilePath = Choosepath.choosefile(1);
        String s = fileController.UploadFile(upFilePath);
        System.out.println(s);

        //测试下载文件
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要下载文件的uuid：");
        String uuid1 = sc.next();
        String s1 = fileController.DownloadFile(uuid1);
        System.out.println(s1);

        //测试查询文件元数据
        System.out.println("请输入查询文件的uuid：");
        String uuid2 = sc.next();
        JSONObject jsonObject = fileController.FileData(uuid2);
        System.out.println("文件的元数据：");
        System.out.println(jsonObject);

    }
}
