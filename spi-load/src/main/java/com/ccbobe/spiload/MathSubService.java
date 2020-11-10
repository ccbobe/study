package com.ccbobe.spiload;
@LoadScope(name = "MathSubService",order = 2)
public class MathSubService implements MathService {
    @Override
    public Integer add(Integer a, Integer b) {
        return a - b;
    }
}
