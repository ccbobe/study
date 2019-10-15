package com.bobe.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TimeClient {
    
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1);
    
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.AUTO_CLOSE,false)
                    .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel sh) throws Exception {
                    ChannelPipeline pipeline = sh.pipeline();
                    pipeline.addLast("1",new LoggingHandler(LogLevel.INFO));
                    pipeline.addLast("4",new TimeClientHandler());
                }
            });
    
            ChannelFuture future = bootstrap.connect("127.0.0.1",80).sync();
    
            //优雅断开连接
            future.channel().closeFuture().sync();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
        }
    }
}
