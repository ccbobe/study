package com.ccbobe.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MailServiceApplication {

    @Autowired
    private JavaMailSender sender;

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }


    @PostConstruct
    public void sender(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("634231604@qq.com");//发送者
        msg.setTo("luyang@fang.com");//接收者
        msg.setSubject("邮箱发送测试");//标题
        msg.setCc();
        msg.setText("hello world  哈哈哈");//内容
        sender.send(msg);
    }

}
