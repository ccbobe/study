# elasticsearch 学习

## 【一】es 常见指令使用

默认es 安装完可以访问地址http://localhost:9200 查看es 安装情况

常见命令：

(1)查询集群健康状态

_cat/cluster/health?pretty

状态说明： green :状态良好，集群功能齐全

yellow:所有数据可用，但是尚有集群副本没有分派（集群功能齐全）

red:某些数据因任何原因不可用，集群部分功能受限

(2) 查看集群节点

_cat/nodes?v (带表头显示)

(3)  查看索引列表

_cat/indices?v

(4) 创建索引

put  /indexName

(5) 设置索引属性

PUT /users/_settings 
{

  "number_of_replicas":2
}

(6)获取索引属性

GET /users/_settings

GET /users/_doc/1

修改数据 更新修改数据

POST /users/_update/1?pretty
{
  "doc": { "user": {"name": "Jane Doe", "age": 20} }
}

修改文档属性

POST /users/_update/1?pretty
{
 "script":"ctx._source.user.age += 5"
}

