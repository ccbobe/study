package com.ccbobe.thrift;

import com.fasterxml.jackson.core.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Scope("prototype")
@Slf4j
@Component
public class UserServiceClient implements DisposableBean {

    private  TTransport transport = null;

    private UserService.Client client = null;

    private UserService.AsyncClient userAsyncClient = null;

    private DateService.Client dateClient = null;

    @PostConstruct
    public UserService.Client initUserClient(){
        try {
            transport = new TSocket("0.0.0.0", 9999, 30000);

            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            UserService.Client client = new UserService.Client(protocol);

            transport.open();
            this.client = client;
            return client;

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostConstruct
    public DateService.Client initDateClient(){
        try {
            transport = new TSocket("0.0.0.0", 9998, 30000);

            // 协议要和服务端一致
            TProtocol protocol = new TCompactProtocol(transport);

            DateService.Client client = new DateService.Client(protocol);

            transport.open();
            dateClient = client;
            return client;
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserService.Client getClient() {
        return client;
    }

    public void setClient(UserService.Client client) {
        this.client = client;
    }

    public DateService.Client getDateClient() {
        return dateClient;
    }

    @Override
    public void destroy() throws Exception {
        if (null != transport) {
            transport.close();
        }
    }

    public UserService.AsyncClient getUserAsyncClient() {
        return userAsyncClient;
    }

    @PostConstruct
    public UserService.AsyncClient initUserAsyncClient(){
        try {

            // 协议要和服务端一致
            // 客户端应该使用非阻塞 IO
            TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
            TNonblockingTransport transport = new TNonblockingSocket("0.0.0.0",9997,50000);
            TAsyncClientManager tAsyncClientManager = new TAsyncClientManager();
            UserService.AsyncClient client = new UserService.AsyncClient(tProtocolFactory,tAsyncClientManager,transport);
            this.userAsyncClient = client;
            return userAsyncClient;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
