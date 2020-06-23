package com.bobe.netty.pushserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<PushData> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PushData msg) throws Exception {
        System.out.println(msg.toJson().toString()+"====>channelRead0");
        ctx.writeAndFlush(msg.toJson().toString());
    }
}
