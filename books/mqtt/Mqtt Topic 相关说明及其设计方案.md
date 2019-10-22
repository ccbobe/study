Mqtt 订阅消息Topic 设计思路
Mqtt 消息基于pub/sub 方式。
如果想实现点对点发送消息
可以采用 parentTopic/p2p/ClientId 的方式，客户端只需要订阅自己的专属通道即可完成点对点消息。
服务端可以采用 类似 parentTopic/serverBiz/ClientId 这种方式动态接受消息。方便完成服务端集群部署。

MQTT消息之间订阅关系
