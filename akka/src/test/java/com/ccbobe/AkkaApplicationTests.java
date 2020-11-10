package com.ccbobe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AkkaApplicationTests {

    @Test
    void contextLoads() {



    }


    public static void main(String[] args) {
        List<String> arrs = new ArrayList<>();
        arrs.add("1");
        arrs.add("2");
        arrs.add("3");

        for (int i = 0; i < 10; i++) {
            if (i<arrs.size()){
                System.out.println(arrs.get(i));
            }else {
                System.out.println("*");
            }
        }
    }

}
