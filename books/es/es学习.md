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


新增文档
POST /products/_doc/5
{
  
  "name" : "蓝月亮洗衣液",
  "count" : 65,
  "price" : 55.6,
  "tag" : [
    "不伤手",
    "泡沫多",
    "除菌"
  ]
}

查询文档
GET /products/_search
{
  "query": {
    "match_all": {
    }
  }
}

修改文档属性
POST /products/_update/1
{
  "script": "ctx._source.name='新奇洗衣粉'"
}

新增文档
POST /products/_doc/5
{
  "name" : "蓝月亮洗衣液",
  "count" : 65,
  "price" : 55.6,
  "tag" : [
    "不伤手",
    "泡沫多",
    "除菌"
  ]
}
### 聚合查询
POST /products/_search
{
  "size": 0, 
  "aggs": {
    "tag_group": {
      "terms": {
        "field":"count"
      }
    }
  }
}
利用分词查询产品信息
GET /products/_search
{
 "query": {
   "match": {
      "name": {
        "query": "蓝月亮 洗衣粉"
        , "analyzer": "ik_max_word"
      }
   }
 },
 "highlight": {
   "fields": {"name": {}}
 }
}

###将sql 语句查询改造成为dsl 查询语句

POST /_sql/translate
{
    "query": "SELECT * FROM products where name like '%洗衣粉%' ORDER BY price DESC",
    "fetch_size": 10
}

