package com.weiran.login.obj;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Msg implements Serializable {
    private String msg;
    private Integer code;
    Map<String,Object> extend = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setMsg("处理成功");
        result.setCode(200);
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理失败");
        return result;
    }

    public Msg add(String key, Object value) {
        this.extend.put(key, value);
        return this;
    }
}

