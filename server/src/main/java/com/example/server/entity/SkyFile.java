package com.example.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 *
 * 文件实体
 *
 * @author stream
 * @since 2021/11/2 22:36
 */
@Data
@AllArgsConstructor
@TableName(value = "lockfile")
public class SkyFile {

    @TableId(value = "fileid",type = IdType.AUTO)
    private Integer fileid;

    private String uuid;

    private String filename;

    private String filetype;

    private Long filesize;

    private Date createtime;

    private String fileaddress;



    public SkyFile(String uuid, String filename, Long filesize, String filetype, Date createtime,
                   String fileaddress) {
        this.uuid = uuid;
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.createtime = createtime;
        this.fileaddress = fileaddress;
    }
}
