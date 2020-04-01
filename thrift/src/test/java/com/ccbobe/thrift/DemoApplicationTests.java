package com.ccbobe.thrift;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests {

	 @Test
	 public void jsonFormatJava() {
		String json = "{ \"code\":\"code\" ,\"data\":{ \"age\":\"18\",\"name\":\"ccbobe\" }}";
		Status<User> userStatus = JSON.parseObject(json, new TypeReference<Status<User>>(){});
		System.out.println(userStatus.getData().getAge());

	}

}
@Data class Status<T> {

	private String code;

	private T data;
}
@Data
class User{
	private Integer age;

	private String name;
}