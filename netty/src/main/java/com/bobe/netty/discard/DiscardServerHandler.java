package com.bobe.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class DiscardServerHandler extends SimpleChannelInboundHandler {
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        
        ((ByteBuf)msg).release();
    }
}
