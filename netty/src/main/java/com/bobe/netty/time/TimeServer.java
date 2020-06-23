package com.bobe.netty.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class TimeServer {
    //时间服务
    
    public static void main(String[] args) {
    
        EventLoopGroup boss = null;
        
        EventLoopGroup worker = null;
        try {
            
            if (Epoll.isAvailable()){
            
                boss = new EpollEventLoopGroup(1);
                worker = new EpollEventLoopGroup(1);
               
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

                            ByteBuf buf = Unpooled.copiedBuffer("ff".getBytes());
                            pipeline.addLast("1",new LoggingHandler(LogLevel.INFO));
                           // pipeline.addLast("de",new DelimiterBasedFrameDecoder(4096,false,buf));
                             pipeline.addLast("StringDecoder",new StringDecoder());

                            pipeline.addLast("StringEncoder",new StringEncoder());
                            pipeline.addLast("4",new SimpleHandler());

                        }
                    });
            //
            ChannelFuture future = bootstrap.bind("127.0.0.1",8080).sync();
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
