package com.bobe.netty.pushserver;

import lombok.Data;
import org.json.JSONObject;

import java.io.Serializable;
@Data
public class PushData implements Serializable {

    private String start;

    private String cmd;

    private String data;

    private String qasType;


    // create by build json plugin
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("start", start);
        jo.put("cmd", cmd);
        jo.put("data", data);
        jo.put("qasType", qasType);
        return jo;
    }
}
