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

   

    

   

