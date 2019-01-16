package com.ccbobe.study.message;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Shop implements Serializable {


    private static final long serialVersionUID = -93769956076380178L;

    private LocalDate date = LocalDate.now();

    private String name;

    private Integer orderId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
