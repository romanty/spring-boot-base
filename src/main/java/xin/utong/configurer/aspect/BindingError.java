package xin.utong.configurer.aspect;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yutong on 2017/7/14.
 */
@Data
public class BindingError implements Serializable {
    private static final long serialVersionUID = 8009386880334435785L;

    /**
     *  消息内容
     */
    private String message;

    /**
     *  字段名
     */
    private String name;
}
