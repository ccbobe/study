package com.ccbobe.codec;

import com.ccbobe.core.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author ccbobe
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg.getUuid().getBytes());
        buf.writeInt(msg.getType());
        buf.writeBytes(msg.getData().getBytes());
        buf.writeBytes("\n".getBytes());

    }
}
