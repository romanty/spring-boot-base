package xin.utong.core;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 * Created by apple on 2017/7/6.
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
