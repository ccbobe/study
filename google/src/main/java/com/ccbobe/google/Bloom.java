package com.ccbobe.google;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class Bloom {
    public static void main(String[] args) {
        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),100000,0.00001);
        b.put("121");
        b.put("122");
        b.put("123");
        System.out.println(b.mightContain("123"));
    }
}
