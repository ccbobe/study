**Netty学习及其重要笔记说明**

ChannelOptions 中参数说明及其用途：
**SO_BACKLOG**:对应的是tcp/ip协议listen函数中的backlog参数。函数listen (int socketfd,int backlog) 用来初始化服务器端可连接队列，
服务器处理客户端连接请求是顺序的，所以同一时间只能处理一个客户端的连接。多个客户端来对来的时候，服务器将不能处理的连接先放
在请求队列中等待处理。backlog 参数是指定队列的大小
**sSO_REUSEADDR**:这个参数对应套接字中选项SO_RESUEADR 这个参数表示允许重复使用本地地址及其端口。
比如：某个服务进程已经监听了套接字中的选项中的 SO_KEEPALIVE ，该参数用在tcp 连接 当该参数设置以后，
连接会测试连接状态。当改参数设置了之后，这个选项将用于能长时间没有数据交流的连接。当该参数设置以后
，如果两小时内没有收到数据的通信时，tcp 将自动发送一个活动探测数据报文。
**ChannelOption.SO_SNDBUF和ChannelOption.SO_RCVBUF**
ChannelOption.SO_SNDBUF参数对应于套接字选项中的SO_SNDBUF，
ChannelOption.SO_RCVBUF参数对应于套接字选项中的SO_RCVBUF
这两个参数用于操作接收缓冲区和发送缓冲区的大小，
接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功，
发送缓冲区用于保存发送数据，直到发送成功。
ChannelOption.SO_LINGER           
ChannelOption.SO_LINGER参数对应于套接字选项中的SO_LINGER,
Linux内核默认的处理方式是当用户调用close（）方法的时候，函数返回，在可能的情况下，
尽量发送数据，不一定保证会发生剩余的数据，造成了数据的不确定性，
使用SO_LINGER可以阻塞close()的调用时间，直到数据完全发送。

ChannelOption.TCP_NODELAY参数对应于套接字选项中的TCP_NODELAY,该参数的使用与Nagle算法有关,
Nagle算法是将小的数据包组装为更大的帧然后进行发送，而不是输入一次发送一次,
因此在数据包不足的时候会等待其他数据的到了，组装成大的数据包进行发送，
虽然该方式有效提高网络的有效负载，但是却造成了延时，而该参数的作用就是禁止使用Nagle算法，
使用于小数据即时传输，于TCP_NODELAY相对应的是TCP_CORK，该选项是需要等到发送的数据量最大的时候，
一次性发送数据，适用于文件传输。

**netty 源码学习及其重要概念掌握。**
当我们进行数据传输的时候，往往需要使用到缓存区。常用的缓冲区就是jdk 的   nio类库 java.nio.buffer.
实际上除了boolean ，其他基本类型都实现缓冲区。从功能上而言，
byteBUffer 完全可以满足nio 编程的需要。但是由于 NIO 编程的复杂性。ByteBuffer 也是有局限性的。
（1） ByteBuffer 只是有一个标志位置的指针postition.读写的时候需要手工执行调用flip()  和rewind（）
使用者必须消息谨慎使用其中的api .否则很容易导致程序处理失败。
ByteBuffer 的api 功能有限。一下高级和实用的特性他不支持。需要使用者自己变成实现。

ByteBuf 的工作原理。
不同的ByteBuf 实现类的工作原理不经相同。首先ByteBuf 依然是一个Byte 数组的缓冲区。他的基本功能与JDK 中的ByteBUffer 功能一致。
提供一下基本类型的功能。
其中 七中 java  基本类型数组 byte 数组。ByteBuffer 等的读写。
缓存区自身的copy 及其slice 等操作。
设置网络字节序。
构造缓冲区实例。
操作位置指针等方法。

netty  channel 以及Unsafe 类重要API  说明及其解释
channel 为netty 网络操作抽象类。他聚合了一组功能，包括但不限于网络的读写，客户端的发起的连接
，主动关闭连接，包括获取该channel 的eventLoop ，获取缓分配器ByteBufAllocator 和 pipeline 等。


ChannelHandler 由两部分组成 ChannelInBoundHandler 和 ChannelOutBoundHandler 这两种类型。
ChannelInboundHandler 是进站处理器.

ChannelPipeline 为 hanndler连接容器   进站   一次进站  inbound   出站延这outbound 后进行出站（尾部向头部的方向出站）。


netty 事件传递： 
channelPipeline和 channel 的调用
每个channelHandler 被添加到channelPipeline 后，都会创建一个channelHandlerContext并与之创建的ChannelHandler 关联。
ChannelHandlerContext 允许ChannelHandler 与其他的ChannelHandler 实现进行交互。

(事件传递过程)
 1.事件在ChannelPipeline中流经第一个ChannelHandler，进行相应的逻辑处理。
 2.ChannelHandler借助ChannelHandlerContext获得下一个ChannelHandler
 3.下一个ChannelHandler处理，依次循环，直到传递到ChannelPipeline的尾部


(只关注处理，节约系统性能防止事件在整个链条上传递)
 其实ChannelHandlerContext的write方法。
 这里的ChannelHandlerContext的调用可以跳过某些不感兴趣的ChannelHandlerContext，
 可以避免让事件流经整个ChannelPipeline。















