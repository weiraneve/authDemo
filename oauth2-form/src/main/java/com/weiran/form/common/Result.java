package com.weiran.form.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class Result {
    private int code = 200;
    private String msg = "";
    private Map<String, Object> data = new HashMap();

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Result(int code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = (Map)(data == null ? new HashMap() : data);
    }

    public Result() {
    }

    public void addData(String key, Object value) {
        this.data.put(key, value);
    }
}
