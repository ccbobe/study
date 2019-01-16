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

   **消息属性和有效载荷**
   AMQP 模型中的消息Message对象带有属性的。
   Content type :内容类型 
   Content encoding 内容编码
   Routing key 路由键
   Delivery mode (persistent or not)投递模式 持久化或者非持久化
   Message priority 消息优先权
   Message publishing timestamp 消息发布时间戳
   Expiration period 消息有效期 
   Publisher application id 发布应用的ID

   **消息确认**

   ### ConfirmCallback

   通过实现ConfirmCallback接口，消息发送到broker后触发回调，确认消息是否到达broker服务器，也是就只确认是否到达Exchange中，

   ReturnCallBack

   通过实现ReturnCallback 接口  ，启动消息失败返回，比如路由不到队列时触发回调

   

   #### 消息接收确认

   消息通过ACK确认是否被正确接受，每个message 都要被确认（acknowledged）可以手动去Ack  或者自动ACK.

   如果消息已经被处理，但后续抛出异常，也就是消费端没有处理完成这条消息，就相当于丢失了消息。

   如果消息已经被处理，但后续代码抛出异常，使用spring 进行管理的消费端业务逻辑进行回滚，这样也会造成实际意义上的消息丢失。

   如果手动确认消息则当消费者调用ack nack, reject 几种方式进行确认，手动确认消息在业务失败的时候进行一些操作。如果消息没有被确认则发送到下一个消费者。如果某个服务器忘记了ack消息，

   

   

   

   


