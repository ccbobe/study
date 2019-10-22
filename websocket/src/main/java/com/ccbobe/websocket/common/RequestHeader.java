package com.ccbobe.websocket.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ccbobe
 */
@Data
public class RequestHeader implements Serializable {
    /**
     *  消息ID
     */
    private String id;
    /**
     * 消息SN
     */
    private String sn;
    /**
     * 消息类型
     */

    private Byte type;
    /**
     * 消息长度
     */
    private int dataLength;
}
