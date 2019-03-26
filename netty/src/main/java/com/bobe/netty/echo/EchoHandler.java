package com.bobe.netty.echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import java.util.UUID;

public class EchoHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	
	private AsciiString contentType = HttpHeaderValues.APPLICATION_JSON;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
		System.out.println("class:" + fullHttpRequest.getClass().getName());
		DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.OK,
				Unpooled.wrappedBuffer("hello netty 4 ".getBytes())); // 2
		
		HttpHeaders heads = response.headers();
		heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
		heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 3
		heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
		ctx.writeAndFlush(response);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete");
		super.channelReadComplete(ctx);
		System.out.println(ctx.toString());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (ctx!=null){
			ctx.close();
		}
	}
}
