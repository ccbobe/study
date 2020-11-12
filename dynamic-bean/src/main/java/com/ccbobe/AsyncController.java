package com.ccbobe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequestMapping
@RestController
public class AsyncController {

    @Bean
    public ConcurrentHashMap concurrentHashMap(){
        return new ConcurrentHashMap(32);
    }


    public String pullMsg(String msgId,String data){

        return "";
    }

    @RequestMapping("addClass")
    public String addClass(String className) throws Exception {
       String fullName = "com.ccbobe."+className;
        ApplicationContextUtils.addT(fullName);
        return (className);

    }

    @RequestMapping("addClassBySpring")
    public String addClassSearch(String className) {

        try {
            ClassUtils.searchClassByClassName("D:\\data\\","com.ccbobe.LastBase");
            log.info("搜索.....");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return "cabbed";
    }


    @RequestMapping("addClassSearchByJar")
    public String addClassSearchByJar(String className) {

        try {
            ClassUtils.searchClass("d:\\data");
            log.info("搜索.....");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return className;
    }

    @Autowired
    private Environment env;


    @Autowired
    private RunProcess process;

    @RequestMapping("hello")
    public Context hello(Context context){
        context.setId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        context.setName("helloBase");
        Context run = process.run(context);
        return run;
    }

    @RequestMapping("test")
    public Context testBase(Context context){
        context.setId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        context.setName("TestBase");
        Context run = process.run(context);
        return run;
    }


}
