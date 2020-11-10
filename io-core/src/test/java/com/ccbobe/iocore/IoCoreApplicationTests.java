package com.ccbobe.iocore;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Iterator;

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

        System.out.println(reader.readLine());

    }

    @Test
    public void test(){
        System.out.println("你好");
    }

}
