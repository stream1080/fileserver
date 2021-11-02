package com.example.server.controller;


import com.example.server.entity.R;
import com.example.server.entity.SkyFile;
import com.example.server.service.FileServer;
import com.example.server.util.StreamUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * 文件控制器
 *
 * @author stream
 * @since 2021/11/2 22:36
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
public class ServerFileController {

    //本地存储目录
    private String LocationPath = "D:\\ContractLock\\";

    @Autowired
    private FileServer fileServer;

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public R ReceiveFile(HttpServletRequest req) throws Exception {

        //创建文件uuid
        String uuid = UUID.randomUUID().toString();
        //获得新文件名 uuid+.+文件类型
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);
        System.out.println("time: "+time);
        String filetype = req.getHeader("filetype");
        System.out.println("filetype: "+filetype);
        String newdirectory = LocationPath+time;
        String newFilename = newdirectory+"\\"+uuid+"."+filetype;
        //判断上层目录是否存在
        File newFile = new File(newdirectory);
        if (!newFile.exists()){
            log.info("文件目录不存在，新建目录");
            newFile.mkdirs();
        }
        InputStream in = null;
        FileOutputStream out = null;
        try {
            //建立传输流
            in = req.getInputStream();
            //文件输出流
            out = new FileOutputStream(newFilename);
            StreamUtil.OutUtil(in,out);
            in.close();
            out.close();
        } catch (IOException e) {
            return R.error("SkyFile transfer error！");
        }

        //将文件大小、文件类型，原始文件名、创建时间、文件保存目录地址等元数据记录至数据库
        Long FileSize = Long.valueOf(req.getHeader("filesize"));
        String SFName = req.getHeader("filename");
        Date CreateTime = new Date(System.currentTimeMillis());

        SkyFile skyFile = new SkyFile(uuid,SFName,FileSize,filetype,CreateTime,newFilename);
        log.info(uuid);
        fileServer.save(skyFile);

        return R.success(uuid);
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/download")
    public R FileSending(HttpServletRequest req, HttpServletResponse resp) {
        String uuid = req.getHeader("uuid");
        SkyFile skyFile = fileServer.getByUUID(uuid);
        if (skyFile==null){
            return R.error("SkyFile UUID not found！");
        }
        resp.setHeader("filetype",skyFile.getFiletype());
        InputStream in;
        OutputStream out;
        try {
            in = new FileInputStream(skyFile.getFileaddress());
            out = new DataOutputStream(resp.getOutputStream());
            StreamUtil.OutUtil(in,out);
            in.close();
            out.flush();
        } catch (IOException e) {
            return R.error(e.getMessage());
        }
        return R.success("Download succeeded！");

    }


    @ApiOperation(value = "获取文件信息")
    @GetMapping("/metadata")
    public R Filemetadata(String uuid) {
        SkyFile skyFile = fileServer.getByUUID(uuid);
        if (skyFile==null){
            return R.error("SkyFile not found！");
        }
        return R.success("Search succeeded！",skyFile);
    }

}
