package com.ccbobe.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private String uuid; //32

    private int type; //4

    private String data;
}
