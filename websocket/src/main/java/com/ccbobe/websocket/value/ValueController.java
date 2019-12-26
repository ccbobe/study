package com.ccbobe.websocket.value;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("value")
public class ValueController {

    @Autowired
    private Environment environment;
    @Autowired
    private ValueService valueService;

    @Value("${user.name}")
    private String userName;

    @Value("${os.name}")
    private String osName;

    //获取List列表
    @Value("${user.ids}")
    public List<Integer> ids;

    // 获取数组
    @Value("${user.ids}")
    public Integer[] userIds;

    // spel表达式获取
    @Value("#{valueController.ids.get(2)}")
    private Integer id;

   private static String userAge;

    @Value("${user.age}")
    public void setUserAge(String _userAge) {
        userAge = _userAge;
    }


    @RequestMapping("/getUserName")
    public String getUserName(){
        return userName + ": " +osName;
    }

    @RequestMapping("/getUserIds")
    public Integer getUserIds(Integer index){
        if (index>ids.size()-1){
            return 0;
        }
        return ids.get(index);
    }

    @RequestMapping("/getUserIdByArray")
    public Integer getUserIdByArray(Integer index){
        return userIds[index];
    }

    @RequestMapping("/getUserId")
    public Integer getUserId(){
        return id;
    }

    @RequestMapping("/getStaticUserAge")
    public String getStaticUserAge(){
        log.info("获取到静态数据{}",userAge);
        return userAge;
    }

    @RequestMapping("/getEnvironment")
    public String getEvironment(){
        log.info("获取到静态数据{}",environment);
        return environment.getProperty("user.name");
    }

    @RequestMapping("/getExtInfo")
    public String getExtInfo(){
        log.info("获取到静态数据{}");
        return valueService.fullName +":"+ valueService.ext;
    }
}
