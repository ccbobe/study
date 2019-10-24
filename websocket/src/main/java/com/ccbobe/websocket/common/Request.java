package com.ccbobe.websocket.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ccbobe
 */
@Data
public class Request implements Serializable {
    private RequestHeader header;
    private Object data;
}
