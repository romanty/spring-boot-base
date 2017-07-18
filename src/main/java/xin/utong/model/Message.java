package xin.utong.model;

import lombok.Data;

/**
 * 消息对象
 * Created by yutong on 2017/7/17.
 */
@Data
public class Message<T> {
    private long id;
    private int type;
    private T data;
}
