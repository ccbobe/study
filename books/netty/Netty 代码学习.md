(1) 开发netty程序主要扩展适配器类，主要有：
1 ChannelHandlerAdapter
2 ChannelInboundHandlerAdapter
3 ChannelOutBoundHandlerAdapter
4 ChannelDuplexHandler
(2) 使用Netty 接受并发送一条消息，就会发生一条数据转换。入站消息将会被解码，就是说：从字节转换为另一种格式。
通常是一个Java对象。如果是出站消息，则将是反向转换。（发生转换的原因主要是网络数据总是一系列的字节）。

(3) 编码器和解码器
编码器：
消息对象编码成消息对象
消息对象编码成字节码
MessageToByteEncoder和MessageToMessageEncoder

解码器：
解码字节到消息
解码消息到消息
解码消息到字节
 
编解码器：
