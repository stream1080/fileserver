package com.example.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 公共返回对象
 *
 * @author stream
 * @since 2021/11/2 22:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static R success(String message){
        return new R(200,message,null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static R success(String message, Object obj){
        return new R(200,message,obj);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static R error(String message){
        return new R(401,message,null);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static R error(String message, Object obj){
        return new R(500,message,obj);
    }


}
