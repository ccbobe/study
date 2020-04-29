package com.ccbobe.websocket.map;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapTest {
    public static void testPut(){
        Map<Integer,String> map = new HashMap();
        map.put(1,"2");
    }

    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        int num = sc.nextInt();
        Scanner sc2=new Scanner(System.in);
        Map<Integer,Integer> map = new HashMap<>(num);

    }
}
