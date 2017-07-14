package xin.utong.core;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 * Created by apple on 2017/7/6.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 6160056333237791400L;
    private int code;
    private String message;
    private T data;

    public Result<T> setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
