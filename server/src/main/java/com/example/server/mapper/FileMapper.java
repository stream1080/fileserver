package com.example.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.SkyFile;
import org.springframework.stereotype.Component;

/**
 * @author stream
 * @since 2021/11/2 22:36
 */
@Component
public interface FileMapper extends BaseMapper<SkyFile> {

    SkyFile selectByUUID(String uuid);
}
