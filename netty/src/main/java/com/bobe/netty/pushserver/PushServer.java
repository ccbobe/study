package com.bobe.netty.pushserver;

import com.bobe.netty.echo.EchoHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class PushServer {

    public static void main(String[] args) {
        //接受线程
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        //工作线程
        NioEventLoopGroup worker = new NioEventLoopGroup(8);
        try {
            //引导程序启动
            ServerBootstrap bootstrap = new ServerBootstrap();
            //绑定服务器端操作参数
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel sh) throws Exception {
                            ChannelPipeline pipeline =sh.pipeline();
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast("",new DelimiterBasedFrameDecoder(4096,true,Unpooled.copiedBuffer("ff".getBytes())));
                            pipeline.addLast("1",new StringDecoder());
                            pipeline.addLast("2",new StringEncoder());
                            pipeline.addLast("3",new ProtocolDecoder());
                            pipeline.addLast("4",new ServerHandler());
                        }
                    });


            ChannelFuture future = bootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
