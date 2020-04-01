package com.ccbobe.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class InitThriftServer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        initUserServer();
    }


    private void initUserServer()throws Exception{
        log.info("initUserServer");
        new Thread(){
            @Override
            public void run(){
                log.info("启动远程服务");
                try {
                    TProcessor processor = new UserService.Processor<UserService.Iface>(new UserProvider());

                    // 简单的单线程服务模型,阻塞IO
                    TServerSocket serverTransport = new TServerSocket(9999);
                    TServer.Args tArgs = new TServer.Args(serverTransport);
                    tArgs.processor(processor);
                    ////使用二进制协议
                    tArgs.protocolFactory(new TBinaryProtocol.Factory());
                    //创建服务器
                    TServer server = new TSimpleServer(tArgs);
                    System.out.println("HelloServer start....");
                    server.serve(); // 启动服务
                } catch (Exception e) {
                    log.error("请求错误{}",e);
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                log.info("启动远程服务");
                try {
                    TProcessor processor = new DateService.Processor<DateService.Iface>(new DateProvider());

                    // 简单的单线程服务模型,阻塞IO
                    TServerSocket serverTransport = new TServerSocket(9998);
                    TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
                    tArgs.processor(processor);
                    ////使用二进制协议
                    tArgs.protocolFactory(new TBinaryProtocol.Factory());
                    //tArgs.transportFactory(new TFramedTransport.Factory());
                    //创建服务器
                    tArgs.executorService(Executors.newCachedThreadPool());
                    TServer server = new TThreadPoolServer(tArgs);
                    System.out.println("DateServer start....");
                    server.serve(); // 启动服务
                } catch (Exception e) {
                    log.error("请求错误{}",e);
                }
            }
        }.start();

    }

}
