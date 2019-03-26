package com.bobe.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class EchoClient {
	
	public static void main(String[] args) {
		NioEventLoopGroup loopGroup = new NioEventLoopGroup(1);
		
		Bootstrap bootstrap = new Bootstrap();
		
		try {
			bootstrap.group(loopGroup);
			bootstrap.channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
				
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					ChannelPipeline pipeline = socketChannel.pipeline();
					pipeline.addLast("logging",new LoggingHandler(LogLevel.INFO));
					pipeline.addLast("decoder", new HttpRequestEncoder());   // 1 用于编码request
					pipeline.addLast("encoder", new HttpResponseDecoder()); // 2 用于解码response
					pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));   // 3
					pipeline.addLast(new IdleStateHandler(1, 1, 5));
					pipeline.addLast("simple",new ServerSimpleHandler());
				}
			}).connect("127.0.0.1",8080).sync().channel().closeFuture().sync();
			
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} finally {
			loopGroup.shutdownGracefully();
		}
		
		
	}
}
