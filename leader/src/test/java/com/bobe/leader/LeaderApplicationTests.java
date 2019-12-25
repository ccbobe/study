package com.bobe.leader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaderApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Test
	public void testFindFileLists() throws Exception{
		//listFiles(new File("D:\\files"));
		System.out.println(fub(20000));
	}


	/**
	 * 递归列举出当前指定文件下文件列表
	 * @param file
	 */
	public void listFiles(File file){
		if (!file.isDirectory()){
			System.out.println("file名称====>"+file.getName());
		}
		if (file.isDirectory()){
			File[] files = file.listFiles();
			for (File f :files) {
				listFiles(f);
			}
		}
	}

	/**
	 * 斐波那契数列 计算
	 * @param n
	 * @return
	 */
	public Integer fub(int n) {//斐波那契数列
		if (n<0){
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fub(n - 1) + fub(n - 2);
		}
	}
}
