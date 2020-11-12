package com.ccbobe.websocket.env;

/**
 * @author ccbobe 测试环境生命周期
 */
public class ValueInfo {

    private String envProp;

    private String data;

    public ValueInfo(String envProp) {
        this.envProp = envProp;
    }

    public ValueInfo() {
    }

    public void setData(){
        data = EnvironmentUtils.searchByKey("spring.application.name");
        System.out.println(data);
    }

}
