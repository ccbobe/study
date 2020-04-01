package com.ccbobe.message;

import com.ccbobe.core.NettyClient;
import io.netty.handler.codec.Delimiters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


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
    @RequestMapping("getData")
    public String getData(String url){
        try {
            URL rtsp = new URL(url);
            URLConnection urlConnection = rtsp.openConnection();
            String rtspProtocol = rtsp.getProtocol();
            urlConnection.connect();
            InputStream stream = urlConnection.getInputStream();
            return rtspProtocol;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
