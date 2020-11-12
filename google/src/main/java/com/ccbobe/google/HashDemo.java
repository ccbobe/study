package com.ccbobe.google;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class HashDemo {

    public static void main(String[] args) {
        // 计算MD5
        String input = "hello world";
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.md5().hashString(input, Charsets.UTF_8));
        // 计算sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        // 计算sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        // 计算crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());

    }
}
