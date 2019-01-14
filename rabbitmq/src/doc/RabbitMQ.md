# **RabbitMQ 学习基础知识**

1. 几个重要概念

   Exchange ：交换机：决定了消息路由规则

   Queue ：消息队列

   Bind ：绑定了Queue和Exchange ,其意义在于符合什么样的路由规则的消息将会放置在那个消息规则的队列

   Broker ：简单来说就是消息队列服务器实体

   Routing Key:  路由 关键字，exchange  将根据这个关键字进行消息投递

   vhost:虚拟主机：一个broker实体中可以开设几个不同的虚拟主机，作用不同用户的权限分离

   producer：消息生产者，就是投递消息的程序

   customer:消息消费者，就是接受消息的程序

   channel ：消息通道，在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务 
   
   API 说明:ConnectionFactory ,Connection ,Channel都是rabbitMQ  对外提供的API的最基本的对象，Connection代表RabbitMQ的Socket连接信息
   他分装socket相关协议，ConnectionFactory为Connection   的制造工厂。
   Channel   是我们与RabbitMQ 打交道的最重要的接口，我们的大部分业务操作实在Channel这个接口中完成的，包括定义Queue,定义Exchange,
   绑定queue 和发布消息等。
   Connection 为建立的TCP 连接信息，生产者都是通过消费者和生产者都是通过channel 进行连接的。
   Channel 这是虚拟连接 建立在TCP连接基础上。数据流都是通过channel 进行的。
   
   **ACK确认机制**
   每个Constomer 可能需要一段时间才能处理完接受到的消息:如果没有确认消息机制，默认消息则会被消息队列删除了。
   为了保证消息不会丢失，rabbitMQ支持消息确认机制，即Acknowlegaement.而应在消息处理完成的时候发送ack表示数据处理完成。
   
   

   

    

   

