package com.ccbobe.google;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class Bloom {
    public static void main(String[] args) {
        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),100000,0.00001);
        b.put("121");
        b.put("122");
        b.put("123");
        System.out.println(b.mightContain("123"));

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),100,0.0000000000001);
        bloomFilter.put("121");
        bloomFilter.put("122");
        bloomFilter.put("123");
        System.out.println(bloomFilter.expectedFpp());

        System.out.println(bloomFilter.mightContain("1"));


        HashFunction crc32 = Hashing.crc32();

    }
}
