package com.ccbobe.spiload;
@LoadScope(name = "MathAddService",order = 1)
public class MathAddService implements MathService {

    @Override
    public Integer add(Integer a, Integer b) {
        return a+b;
    }
}
