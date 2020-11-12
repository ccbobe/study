package com.ccbobe.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author ccbobe
 */
public class MessageDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ByteBuf buf = in.readBytes(32);

        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bytes);
        String uuid = new String(bytes, 0, buf.readableBytes());
        out.add(uuid);

        int   type = in.readInt();
        out.add(type);

        int readableBytes = in.readableBytes();

        byte[] data = new byte[in.readableBytes()];
        in.getBytes(in.readerIndex(), data);
        String body = new String(bytes, 0, buf.readableBytes());
        out.add(body);

    }
}
