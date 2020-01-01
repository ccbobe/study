package com.ccbobe.message;

import com.ccbobe.core.NettyClient;
import io.netty.handler.codec.Delimiters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ccbobe
 */
@RestController
public class MessageController {
    @Autowired
    private NettyClient nettyClient;

    @RequestMapping("sendMessage")
    public String sendMessage(String msg){
        nettyClient.getChannel().writeAndFlush(msg + "\r\n");
        return msg;
    }

    @RequestMapping("sendInteger")
    public Integer sendInteger(Integer msg){
        nettyClient.getChannel().writeAndFlush(Integer.MAX_VALUE);
        return msg;
    }


}
