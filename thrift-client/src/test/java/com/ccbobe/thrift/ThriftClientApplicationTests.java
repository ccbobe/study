package com.ccbobe.thrift;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThriftClientApplicationTests {

    @Test
    void contextLoads() {
        Integer integer = testFinally();
        System.out.println(integer);
    }


    public Integer testFinally(){
        try {
            Integer count = 0;
            count = 100/1000;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NumberFormatException();
        } finally {
            return -2;
        }
    }

}
