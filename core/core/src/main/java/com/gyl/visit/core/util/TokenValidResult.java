package com.gyl.visit.core.util;

import com.gyl.visit.core.inf.ValidResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ganyinglong
 **/
public class TokenValidResult implements ValidResult {

    private Object data;

    public TokenValidResult(int code, String message) {
        this.code = code;
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public TokenValidResult(int code) {

    }

    private int code;
    private List<String> messages;

    @Override
    public int resultCode() {
        return code;
    }

    @Override
    public boolean isValid() {
        return code > 0;
    }

    @Override
    public String message() {
        if (messages == null) {
            return "";
        }
        if (messages.size() == 1) {
            return messages.get(0);
        }
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            msg.append(i).append(":").append(messages.get(i)).append("\n");
        }
        return msg.toString();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
