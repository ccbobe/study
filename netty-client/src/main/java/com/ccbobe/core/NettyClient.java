package com.ccbobe.core;

import com.ccbobe.codec.IntegerDecoder;
import com.ccbobe.codec.IntegerEncoder;
import com.ccbobe.codec.MessageDecoder;
import com.ccbobe.codec.MessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.convert.Delimiter;
import org.springframework.stereotype.Component;

/**
 * @author ccbobe
 */
@Slf4j
@Component
public class NettyClient implements InitializingBean {

    private Channel channel;

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(){
            @Override
            public void run() {
                connect(8081);
            }
        }.start();

    }

    public Channel getChannel(){
        return channel;
    }

    public void connect(Integer serverPort){
        EventLoopGroup boss = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.AUTO_CLOSE,false)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sh) throws Exception {
                            ChannelPipeline pipeline = sh.pipeline();


                          //  pipeline.addLast(new DelimiterBasedFrameDecoder(1024*10*10,Delimiters.lineDelimiter()));
                           // pipeline.addLast(new StringDecoder());
                           // pipeline.addLast(new StringEncoder());



                           // pipeline.addLast(new MessageEncoder());
                            //pipeline.addLast(new MessageDecoder());

                            pipeline.addLast("1",new LoggingHandler(LogLevel.INFO));
                           // pipeline.addLast(new IntegerEncoder());
                            //pipeline.addLast(new IntegerDecoder());

                            pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE,
                                    ClassResolvers.weakCachingConcurrentResolver(this.getClass()
                                            .getClassLoader())));
                            pipeline.addLast(new ObjectEncoder());

                            pipeline.addLast(new MessageHandler());

                            pipeline.addLast(new IdleStateHandler(5,5,30));
                        }
                    });

            ChannelFuture future = bootstrap.connect("127.0.0.1",serverPort).sync();
            channel = future.channel();
            future.channel().closeFuture().sync().await();
        } catch (InterruptedException e) {
            log.info("连接服务器出现异常。。。。。{}",e);

        } finally {
            boss.shutdownGracefully();
        }

    }
}
