package com.example.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.SkyFile;

/**
 *
 * 文件服务接口
 *
 * @author stream
 * @since 2021/11/2 22:36
 */
public interface FileServer extends IService<SkyFile> {
    SkyFile getByUUID(String uuid);
}
