package cn.scnu.edu.entity;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

/**
 * 响应实体类封装，Rest风格
 * @param code 状态码
 * @param data 响应数据
 * @param message 其他消息
 * @param <T> 响应数据类型
 */
public record RestBean<T>(int code, T data, String message) {
    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200,data,"请求成功");
    }

    public static <T> RestBean<T> success(){
        return success(null);
    }
    public static <T> RestBean<T> unauthorized(String message){
        return faliure(401,message);
    }
    public static <T> RestBean<T> forbidden(String message){
        return faliure(403,message);
    }
    public static <T> RestBean<T> faliure(int code, String message) {
        return new RestBean<>(code, null, message);
    }
    /**
     * 快速将当前实体转换为JSON字符串格式
     * @return JSON字符串
     */
    public String asJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
