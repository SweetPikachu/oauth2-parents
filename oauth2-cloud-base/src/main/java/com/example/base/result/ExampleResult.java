package com.example.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class ExampleResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public ExampleResult(){

    }
    public ExampleResult(Object data){
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public ExampleResult(String message, Object data){
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public ExampleResult(Integer code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ExampleResult ok(){return new ExampleResult(null);}
    public static ExampleResult ok(String message) {
        return new ExampleResult(message, null);
    }
    public static ExampleResult ok(Object data) {
        return new ExampleResult(data);
    }
    public static ExampleResult ok(String message, Object data) {
        return new ExampleResult(message, data);
    }

    public static ExampleResult build(Integer code, String message) {
        return new ExampleResult(code, message, null);
    }

    public static ExampleResult build(Integer code, String message, Object data) {
        return new ExampleResult(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 ExampleResult 对象
     * @param json
     * @return
     */
    public static ExampleResult format(String json) {
        try {
            return JSON.parseObject(json, ExampleResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
