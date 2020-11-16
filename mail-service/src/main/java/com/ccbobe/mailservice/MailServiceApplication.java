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
        msg.setFrom("2442313959@qq.com");//发送者
        msg.setTo("1132717195@qq.com");//接收者
        msg.setSubject("测试邮箱服务");//标题
        msg.setText("test hello world");//内容
        sender.send(msg);
    }

}
