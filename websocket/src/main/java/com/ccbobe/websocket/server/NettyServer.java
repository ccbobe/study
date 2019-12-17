package com.ccbobe.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class NettyServer implements InitializingBean {

    @Value("${netty.port}")
    private Integer port;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void initNetty(){
        EventLoopGroup boss = null;

        EventLoopGroup worker = null;
        try {
            if (Epoll.isAvailable()){
                boss = new EpollEventLoopGroup(1);
                worker = new EpollEventLoopGroup(10);
            }else {
                System.out.println("NIO start.....");
                boss = new NioEventLoopGroup(1);
                worker = new NioEventLoopGroup(1);
            }
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("1",new LoggingHandler(LogLevel.INFO));

                        }
                    });
            //
            ChannelFuture future = bootstrap.bind("127.0.0.1",port).sync();
            //
            future.channel().closeFuture().sync().await();

        } catch (Exception e) {
            e.fillInStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
