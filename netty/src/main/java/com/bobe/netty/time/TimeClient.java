package com.bobe.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

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
                    pipeline.addLast(new StringEncoder());
                    pipeline.addLast(new StringDecoder());
                    pipeline.addLast(new IdleStateHandler(5,5,30));
                }
            });
    
            ChannelFuture future = bootstrap.connect("127.0.0.1",8082).sync();

            future.channel().closeFuture().sync().await();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
        }
    }
}
