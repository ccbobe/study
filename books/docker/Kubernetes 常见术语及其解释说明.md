
 一、Kubernetes 常见术语及其解释说明
Pod 
Container:容器
Label:标签
Replication Controller: 复制控制器
Service: 服务
Node: 节点
Kubernetes  Mater:主节点

Pod（上图绿色方框）安排在节点上，包含一组容器和卷。同一个Pod里的容器共享同一个网络命名空间，
可以使用localhost互相通信。Pod是短暂的，不是持续性实体。   
Pod是kubernetes项目的原子调度单位，如果说容器的本质是进程，那么Kubernetes是操作系统
kubernetes项目中，pod是原子调度单位，这就意味着，Kubernetes项目的调度器，是按照整个pod的资源去进行需求计算的。
    
如果Pod是短暂的，那么我怎么才能持久化容器数据使其能够跨重启而存在呢？ 是的，Kubernetes支持卷的概念，因此可以使用持久化的卷类型。
是否手动创建Pod，如果想要创建同一个容器的多份拷贝，需要一个个分别创建出来么？可以手动创建单个Pod，但是也可以使用Replication Controller使用Pod模板创建出多份拷贝，下文会详细介绍。
如果Pod是短暂的，那么重启时IP地址可能会改变，那么怎么才能从前端容器正确可靠地指向后台容器呢？这时可以使用Service，下文会详细介绍。

节点（上图橘色方框）是物理或者虚拟机器，作为Kubernetes worker，通常称为Minion。每个节点都运行如下Kubernetes关键组件：
Kubelet：是主节点代理。
Kube-proxy：Service使用其将链接路由到Pod，如上文所述。
Docker或Rocket：Kubernetes使用的容器技术来创建容器。

Kubelet 常用命令及其结束说明



