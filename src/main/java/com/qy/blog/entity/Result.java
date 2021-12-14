package com.qy.blog.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装返回结果Json
 */
public class Result<E> implements Serializable{

    private Integer code;//状态码
    private String message;//返回提示信息，主要用于页面提示信息
    private E data;//返回数据

    public Result(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public Result(Integer code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /** 出现异常时调用 */
    public Result(Throwable e) {
        // 获取异常对象中的异常信息
        this.message = e.getMessage();
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public E getData() {
        return data;
    }
    public void setData(E data) {
        this.data = data;
    }

    public static void ResultError(String msg, HttpServletResponse response) throws IOException {
        response.setContentType(Constants.JSON_CONTENT_TYPE);
        ServletOutputStream outputStream = response.getOutputStream();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 10000);
        resultMap.put("msg", msg);
        outputStream.write(new ObjectMapper().writeValueAsString(resultMap).getBytes());
        outputStream.flush();
        outputStream.close();
    }

    public static void ResultSuccess(String msg, HttpServletResponse response) throws IOException {
        response.setContentType(Constants.JSON_CONTENT_TYPE);
        ServletOutputStream outputStream = response.getOutputStream();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 200);
        resultMap.put("msg", msg);
        outputStream.write(new ObjectMapper().writeValueAsString(resultMap).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}