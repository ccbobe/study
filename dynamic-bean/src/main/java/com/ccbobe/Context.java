package com.ccbobe;

import lombok.Data;

/**
 * @author ccbobe
 */
@Data
public class Context<Before,U,After> {
    /**
     * 资源ID
     */
    private String id;
    /**
     * 方法类型
     */
    private String name;

    /**
     * 方法名
     */
    private String method;

    /**
     * 数据之前处理
     */
    private Before  before;
    /**
     * 返回数据结果
     */
    private U result;
    /**
     * 数据之后处理
     */
    private After after;
}
