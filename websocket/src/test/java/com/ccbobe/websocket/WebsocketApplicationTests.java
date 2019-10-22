package com.ccbobe.websocket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsocketApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHashMap(){
		Map map = new HashMap();
		map.put(1,null);
		map.put(2,null);
		System.out.println(map.toString());
	}

}
