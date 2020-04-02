package com.ccbobe.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;

@Slf4j
@RestController
@RequestMapping("users/")
public class UserController {
    @Autowired
    private UserServiceClient client;

    @RequestMapping("addUser")
    public User addUser(@RequestBody User user)throws Exception{
        client.getClient().addUser(user);
        return user;
    }

    @RequestMapping("getUser")
    public User getUser(Long userId)throws Exception{
        User user = client.getClient().getUser(userId);
        return user;
    }


    @RequestMapping("getDate")
    public Response getDate(Long userId)throws Exception{
        Response response = client.getDateClient().getDate();
        return response;
    }

    @RequestMapping("getUserAsync")
    public User getUserAsync(Long userId)throws Exception{
        //设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
        TThreadedSelectorServer  server = null;
        TTransport transport = new TFramedTransport(new TSocket("0.0.0.0",9997,60000));
        //协议要和服务端HelloTNonblockingServer一致,使用高密度二进制协议
        TProtocol protocol = new  TBinaryProtocol(transport);
        transport.open();
        UserService.Client client = new UserService.Client(protocol);
        User user = client.getUser(userId);
        transport.close();
        return user;
    }
}
