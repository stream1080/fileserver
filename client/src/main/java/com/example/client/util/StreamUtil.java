package com.example.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamUtil {

    public static void OutUtil(InputStream in, OutputStream out) throws IOException {
            //创建缓冲区
            byte[] buffer = new byte[1024];
            //输出
            int len = 0;
            while ((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
    }


    public static String InUtil(InputStream is) throws IOException {
        int len;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len=is.read())!=-1){
            stringBuilder.append((char) len);
        }
        return stringBuilder.toString();
    }
}
