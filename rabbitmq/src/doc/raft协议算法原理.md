Raft 分布式原理使用

在Raft系统中，每个节点都有以下几种状态中的一种：
**follower**:所有节点都以follower开始，如果没有收到leader 消息则变称 candidate 状态。
candidate:会像其他结点拉选票。如果的得到大部分的票则成为leader.这个过程叫做leader 选举（leader Election）
**leader**：所有系统的修改都需要先经过leader,每次修改都会写一条日志（log entty）。 leader 收到修改的请求如下：
（在这个过程叫做日志复制 replicate entry）

 复制日志到所有follower节点 （replicate entry）;
 大部分节点响应时才能提交日志；
 通知所有follower节点日志i经提交；
 所有follower 也是提交日志；
 现在整合系统处于一致的状态。

 **选主过程：**
 Leader Election
 当follower 在选举阶段election timeout 内未收到leader 的心跳消息 append enties ) 则变成candidate 状态。**未为了避免选主冲突。这个超时时间一般为150 -300 ms 之间的随机数**。



成为candidate的结点发起新的选举期(election term)去“拉选票”：

1. 重置自己的计时器
2. 投自己一票
3. 发送 **Request Vote消息**

如果接收结点在新term内没有投过票那它就会投给此candidate，并重置它自己的选举超时时间。candidate拉到大部分选票就会成为leader，并定时发送心跳——**Append Entries消息**，去重置各个follower的计时器。当前Term会继续直到某个follower接收不到心跳并成为candidate。

如果不巧两个结点同时成为candidate都去“拉票”怎么办？**这时会发生Splite Vote情况**。两个结点可能都拉到了同样多的选票，难分胜负，选举失败，本term没有leader。之后又有计时器超时的follower会变成candidate，将term加一并开始新一轮的投票。

### 1.2.2 Log Replication

当发生改变时，leader会复制日志给follower结点，这也是通过Append Entries心跳消息完成的。前面已经列举了Log Replication的过程，这里就不重复了。

Raft能够正确地处理网络分区（“脑裂”）问题。假设A~E五个结点，B是leader。如果发生“脑裂”，A、B成为一个子分区，C、D、E成 为一个子分区。此时C、D、E会发生选举，选出C作为新term的leader。这样我们在两个子分区内就有了不同term的两个leader。这时如果 有客户端写A时，因为B无法复制日志到大部分follower所以日志处于uncommitted未提交状态。而同时另一个客户端对C的写操作却能够正确 完成，因为C是新的leader，它只知道D和E。

当网络通信恢复，B能够发送心跳给C、D、E了，却发现“改朝换代”了，**因为C的term值更大，所以B自动降格为follower。然后A和B都回滚未提交的日志，并从新leader那里复制最新的日志。但这样是不是就会丢失更新？**