package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.SkyFile;
import com.example.server.mapper.FileMapper;
import com.example.server.service.FileServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 文件服务实现类
 *
 * @author stream
 * @since 2021/11/2 22:36
 */
@Service
public class FileServerImpl extends ServiceImpl<FileMapper, SkyFile> implements FileServer{

    @Autowired
    private FileMapper FileMapper;

    @Override
    public SkyFile getByUUID(String uuid) {
        return FileMapper.selectByUUID(uuid);
    }
}
