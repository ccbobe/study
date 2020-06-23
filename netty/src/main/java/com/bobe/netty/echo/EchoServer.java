package com.bobe.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class EchoServer {
	
	private AtomicInteger integer = new AtomicInteger(0);
	
	public static void main(String[] args) {
		//接受线程
		NioEventLoopGroup boss = new NioEventLoopGroup(1);
		//工作线程
		NioEventLoopGroup worker = new NioEventLoopGroup(1);
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
							//添加处理器信息
							
						    //pipeline.addLast("logging",new LoggingHandler(LogLevel.INFO));
							pipeline.addLast("decoder", new HttpRequestDecoder());   // 1 用于解码request
							pipeline.addLast("encoder", new HttpResponseEncoder()); // 2 用于编码response
							pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));   // 3
							pipeline.addLast("hello world",new EchoHandler());
							//pipeline.addLast("simpleWrite",new ServerSimpleHandler());
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
