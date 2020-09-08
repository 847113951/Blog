package com.ylb.Msg;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private Integer code;
    private String message;
    private Map<String, Object> extend = new HashMap<>();

    public Msg() {
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

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public static Msg success() {
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMessage("处理成功");
        return msg;
    }

    public static Msg fail() {
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMessage("处理失败");
        return msg;
    }

    public Msg add(String name, Object value) {
        this.getExtend().put(name, value);
        return this;
    }

}
