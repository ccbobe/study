package com.ccbobe.iocore;

import io.vavr.*;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Iterator;

import static io.vavr.API.*;

class IoCoreApplicationTests {

    @Test
    void contextLoads() throws Exception {
        Path toPath = new File("D:\\files\\20200709181014\\123.xlsx").toPath();

        System.out.println(toPath.getNameCount());

        Iterator<Path> iterator = toPath.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next().getFileName());
        }

        File file = new File("D:\\files\\data.txt");

        Files.copy(toPath,Paths.get("D:\\files\\电容器2222.xlsx"));
    }

    @Test
    public void testPathsApi() throws Exception{
        Path path = Paths.get("D:", "files", "data.txt");
        //获取文件名
        System.out.println(path.getFileName());
        //获取文件数
        System.out.println(path.getNameCount());
        // 获取文件路径
        System.out.println(path.getRoot());

        System.out.println(path.normalize());

        //Files.copy(path,Paths.get("D:","files","data_tmp.txt"));
        ArrayList<String> read = new ArrayList<>();
        read.add("江西理工大学是一所历史悠久的大学\r\n");
        Files.write(path,read,Charset.forName("utf-8"));

        //获取文件用户所属
        UserPrincipal owner = Files.getOwner(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println(owner.getName());
        System.out.println(Files.exists(path,LinkOption.NOFOLLOW_LINKS));

        Files.write(path," ccbobe is a boy\r\n".getBytes(),LinkOption.NOFOLLOW_LINKS);

        BufferedReader reader = Files.newBufferedReader(path);
        String str = null;
        while((str = reader.readLine()) != null) {
            System.out.println(str);
        }
        System.out.println(Files.size(path));
        //Files.createLink(Paths.get("D://datas"),path);

        FileStore fileStore = Files.getFileStore(path);
        System.out.println(fileStore.type());
        System.out.println(fileStore.getTotalSpace());
        System.out.println(fileStore.getUnallocatedSpace());

        OutputStream outputStream = Files.newOutputStream(Paths.get("D://files//dd.txt"));
        outputStream.write("1".getBytes());
        outputStream.flush();

    }

    @Test
    public void testVavr(){

        Try<Double> result = Try.of(()->1/1.333);
        if (result.isSuccess()){
            System.out.println(result.get());
        }
        Tuple2<String, Integer> java = Tuple.of("java", 8);
        Tuple3<String, Integer, String> tuple3 = Tuple.of("java", 1, "a");
        String s = java._1;
        System.out.println(s);
        System.out.println(java._2());
        System.out.println(tuple3._3());

    }
    @Test
    public void testMatch(){
        int input = 3;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "*"));
        System.out.println(output);
    }

    @Test
    public void test(){
        System.out.println("你好");
    }




}
