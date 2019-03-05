**mysql 查询及其优化**


常见问题总结

存储引擎

MySQL两种存储引擎 MyISAM 和 InnoDB

字符集及其校验规则

字符集是指一种二进制编码到某类字符符号的映射。校对规则时指某种字符集下的排序规则。mysql 中一种特殊的字符集都对应一种校对规则。

MySQL 中采用的时类似继承的方式指定字符集的默认值。每个数据库以及每张表都有自己的默认值。他们逐层继承。

索引相关的内容：

MySQL使用数据索引的主要有Btree 索引和哈希索引。对于哈希索引来说底层数据结构就是哈希表。因此绝大多数需求为单挑数据查询的时候，可以选择哈希索引。查询性能最快。其余大部分场景，建议选择择Btree索引。

　**MyISAM:** B+Tree叶节点的data域存放的是数据记录的地址。在索引检索的时候，首先按照B+Tree搜索算法搜索索引，如果指定的Key存在，则取出其 data 域的值，然后以 data 域的值为地址读取相应的数据记录。这被称为“非聚簇索引”。

　　**InnoDB:** 其数据文件本身就是索引文件。相比MyISAM，索引文件和数据文件是分离的，其表数据文件本身就是按B+Tree组织的一个索引结构，树的叶节点data域保存了完整的数据记录。这个索引的key是数据表的主键，因此InnoDB表数据文件本身就是主索引。这被称为“聚簇索引（或聚集索引）”。而其余的索引都作为辅助索引，辅助索引的data域存储相应记录主键的值而不是地址，这也是和MyISAM不同的地方。**在根据主索引搜索时，直接找到key所在的节点即可取出数据；在根据辅助索引查找时，则需要先取出主键的值，在走一遍主索引。** **因此，在设计表的时候，不建议使用过长的字段作为主键，也不建议使用非单调的字段作为主键，这样会造成主索引频繁分裂。**



查询缓存使用：

my.cnf 中配置一下信息即可开启查询缓存。

query_cache_type=1

query_cache_size=6000

或执行以下命令可以开启查询缓存。

set global query_cache_type =1

set global query_cache_size=6000

开启查询缓存后在同样的条件下以及数据情况下，会直接在缓存中返回结果。这里的查询条件包括查询本身，当前要查询的数据库，客户端协议版本号等一下可能影响结果的信息。因此任何两个查询在任何字符上的不同都会导致缓存不会命中。如果查询中包含任何用户自定义函数，存储函数，用户变量，临时表。mysql 中的系统表。其查询结果也不会被缓存。

缓存建立后，mysql 的查询缓存系统会跟踪查询中涉及表的每张表，如果这些表（数据结构）发生变化。那么和这张表相关的查询缓存都将失效。

缓存虽然能提高数据库查询性能。但是缓存同时也带来了额外的开销。每次查询都要做缓存操作。实失效还要销毁。（因此开启查询缓存需要谨慎，尤其对于写密集的应用更是如此。如果开启，需要合理控制缓存大小，一般建议设置几十Mb比较合适。此外，**还可以通过sql_cache和sql_no_cache来控制某个查询语句是否需要缓存**）

```
select sql_no_cache count(*) from usr;
```

事务机制：

关系型数据库需要遵循ACID规则：（原子性、一致性、隔离性、持久性）

原子性：事务是最小的执行单位，不允许分割。事务的原子性确保要么全部完成，要么完全不起作用。

一致性：执行事务😝前后，数据库从一个一致状态到另外一个一致状态。

隔离性：并发访问数据库时，一个用户的事务不被其他事务所干扰，各并发访问事务时独立的。

持久性：一个事务提交，对数据库的改变时持久的。

**为了达到上述事务特性，数据库定义了几种不同的事务隔离级别：**

- **READ_UNCOMMITTED（未提交读）:** 最低的隔离级别，允许读取尚未提交的数据变更，**可能会导致脏读、幻读或不可重复读**

- **READ_COMMITTED（提交读）:** 允许读取并发事务已经提交的数据，**可以阻止脏读，但是幻读或不可重复读仍有可能发生**

- **REPEATABLE_READ（可重复读）:** 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，**可以阻止脏读和不可重复读，但幻读仍有可能发生。**

- **SERIALIZABLE（串行）:** 最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，**该级别可以防止脏读、不可重复读以及幻读**。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

  这里需要注意的是：**Mysql 默认采用的 REPEATABLE_READ隔离级别 Oracle 默认采用的 READ_COMMITTED隔离级别.**

  事务隔离机制的实现基于锁机制和并发调度。其中并发调度使用的是MVCC（多版本并发控制），通过行的创建时间和行的过期时间来支持并发一致性读和回滚等特性。



### 锁机制与InnoDB锁算法

**MyISAM和InnoDB存储引擎使用的锁：**

- MyISAM采用表级锁(table-level locking)。
- InnoDB支持行级锁(row-level locking)和表级锁,默认为行级锁

**表级锁和行级锁对比：**

- **表级锁：** Mysql中锁定 **粒度最大** 的一种锁，对当前操作的整张表加锁，实现简单，资源消耗也比较少，加锁快，不会出现死锁。其锁定粒度最大，触发锁冲突的概率最高，并发度最低，MyISAM和 InnoDB引擎都支持表级锁。
- **行级锁：** Mysql中锁定 **粒度最小** 的一种锁，只针对当前操作的行进行加锁。 行级锁能大大减少数据库操作的冲突。其加锁粒度最小，并发度高，但加锁的开销也最大，加锁慢，会出现死锁。

**InnoDB存储引擎的锁的算法有三种：**

- Record lock：单个行记录上的锁
- Gap lock：间隙锁，锁定一个范围，不包括记录本身
- Next-key lock：record+gap 锁定一个范围，包含记录本身

- ### 大表优化

  当MySQL单表记录数过大时，数据库的CRUD性能会明显下降，一些常见的优化措施如下：

1. **限定数据的范围：** 务必禁止不带任何限制数据范围条件的查询语句。比如：我们当用户在查询订单历史的时候，我们可以控制在一个月的范围内。；

2. **读/写分离：** 经典的数据库拆分方案，主库负责写，从库负责读； 3 . **垂直分区：**

   **根据数据库里面数据表的相关性进行拆分。** 例如，用户表中既有用户的登录信息又有用户的基本信息，可以将用户表拆分成两个单独的表，甚至放到单独的库做分库。

   **简单来说垂直拆分是指数据表列的拆分，把一张列比较多的表拆分为多张表。**

   

- 尽量使用`TINYINT`、`SMALLINT`、`MEDIUM_INT`作为整数类型而非`INT`，如果非负则加上`UNSIGNED`
- `VARCHAR`的长度只分配真正需要的空间
- 使用枚举或整数代替字符串类型
- 尽量使用`TIMESTAMP`而非`DATETIME`，
- 单表不要有太多字段，建议在20以内
- 避免使用NULL字段，很难查询优化且占用额外索引空间
- 用整型来存IP

#### 索引

- 索引并不是越多越好，要根据查询有针对性的创建，考虑在`WHERE`和`ORDER BY`命令上涉及的列建立索引，可根据`EXPLAIN`来查看是否用了索引还是全表扫描
- 应尽量避免在`WHERE`子句中对字段进行`NULL`值判断，否则将导致引擎放弃使用索引而进行全表扫描
- 值分布很稀少的字段不适合建索引，例如"性别"这种只有两三个值的字段
- 字符字段只建前缀索引
- 字符字段最好不要做主键
- 不用外键，由程序保证约束
- 尽量不用`UNIQUE`，由程序保证约束
- 使用多列索引时主意顺序和查询条件保持一致，同时删除不必要的单列索引

#### 查询SQL

- 可通过开启慢查询日志来找出较慢的SQL
- 不做列运算：`SELECT id WHERE age + 1 = 10`，任何对列的操作都将导致表扫描，它包括数据库教程函数、计算表达式等等，查询时要尽可能将操作移至等号右边
- sql语句尽可能简单：一条sql只能在一个cpu运算；大语句拆小语句，减少锁时间；一条大sql可以堵死整个库
- 不用`SELECT *`
- `OR`改写成`IN`：`OR`的效率是n级别，`IN`的效率是log(n)级别，in的个数建议控制在200以内
- 不用函数和触发器，在应用程序实现
- 避免`%xxx`式查询
- 少用`JOIN`
- 使用同类型进行比较，比如用`'123'`和`'123'`比，`123`和`123`比
- 尽量避免在`WHERE`子句中使用!=或<>操作符，否则将引擎放弃使用索引而进行全表扫描
- 对于连续数值，使用`BETWEEN`不用`IN`：`SELECT id FROM t WHERE num BETWEEN 1 AND 5`
- 列表数据不要拿全表，要使用`LIMIT`来分页，每页数量也不要太大

#### 系统调优参数

- back_log：back_log值指出在MySQL暂时停止回答新请求之前的短时间内多少个请求可以被存在堆栈中。也就是说，如果MySql的连接数据达到max_connections时，新来的请求将会被存在堆栈中，以等待某一连接释放资源，该堆栈的数量即back_log，如果等待连接的数量超过back_log，将不被授予连接资源。可以从默认的50升至500
- wait_timeout：数据库连接闲置时间，闲置连接会占用内存资源。可以从默认的8小时减到半小时
- max_user_connection: 最大连接数，默认为0无上限，最好设一个合理上限
- thread_concurrency：并发线程数，设为CPU核数的两倍
- skip_name_resolve：禁止对外部连接进行DNS解析，消除DNS解析时间，但需要所有远程主机用IP访问
- key_buffer_size：索引块的缓存大小，增加会提升索引处理速度，对MyISAM表性能影响最大。对于内存4G左右，可设为256M或384M，通过查询`show status like 'key_read%'`，保证`key_reads / key_read_requests`在0.1%以下最好
- innodb_buffer_pool_size：缓存数据块和索引块，对InnoDB表性能影响最大。通过查询`show status like 'Innodb_buffer_pool_read%'`，保证` (Innodb_buffer_pool_read_requests – Innodb_buffer_pool_reads) / Innodb_buffer_pool_read_requests`越高越好
- innodb_additional_mem_pool_size：InnoDB存储引擎用来存放数据字典信息以及一些内部数据结构的内存空间大小，当数据库对象非常多的时候，适当调整该参数的大小以确保所有数据都能存放在内存中提高访问效率，当过小的时候，MySQL会记录Warning信息到数据库的错误日志中，这时就需要该调整这个参数大小
- innodb_log_buffer_size：InnoDB存储引擎的事务日志所使用的缓冲区，一般来说不建议超过32MB
- query_cache_size：缓存MySQL中的ResultSet，也就是一条SQL语句执行的结果集，所以仅仅只能针对select语句。当某个表的数据有任何任何变化，都会导致所有引用了该表的select语句在Query Cache中的缓存数据失效。所以，当我们的数据变化非常频繁的情况下，使用Query Cache可能会得不偿失。根据命中率`(Qcache_hits/(Qcache_hits+Qcache_inserts)*100))`进行调整，一般不建议太大，256MB可能已经差不多了，大型的配置型静态数据可适当调大.
  可以通过命令`show status like 'Qcache_%'`查看目前系统Query catch使用大小
- read_buffer_size：MySql读入缓冲区大小。对表进行顺序扫描的请求将分配一个读入缓冲区，MySql会为它分配一段内存缓冲区。如果对表的顺序扫描请求非常频繁，可以通过增加该变量值以及内存缓冲区大小提高其性能
- sort_buffer_size：MySql执行排序使用的缓冲大小。如果想要增加`ORDER BY`的速度，首先看是否可以让MySQL使用索引而不是额外的排序阶段。如果不能，可以尝试增加sort_buffer_size变量的大小
- read_rnd_buffer_size：MySql的随机读缓冲区大小。当按任意顺序读取行时(例如，按照排序顺序)，将分配一个随机读缓存区。进行排序查询时，MySql会首先扫描一遍该缓冲，以避免磁盘搜索，提高查询速度，如果需要排序大量数据，可适当调高该值。但MySql会为每个客户连接发放该缓冲空间，所以应尽量适当设置该值，以避免内存开销过大。
- record_buffer：每个进行一个顺序扫描的线程为其扫描的每张表分配这个大小的一个缓冲区。如果你做很多顺序扫描，可能想要增加该值
- thread_cache_size：保存当前没有与连接关联但是准备为后面新的连接服务的线程，可以快速响应连接的线程请求而无需创建新的
- table_cache：类似于thread_cache_size，但用来缓存表文件，对InnoDB效果不大，主要用于MyISAM。

分区表：

分区表的有点及其缺点

优点

1.可以让单表存储更多的数据。2.分区表的数据更佳容易维护。可以清除删除单个分区批量删除大量数据。也可以再增加新的分区新增更多的数据。还可以对一个独立的分区经行优化。检查修复等操作。3.部分查询能过够从查询条件确定直接落在少数分区上，速度非常快。

分区表的数据可以分不在不同的物理机器上，从而高效利用多个硬件资源。

可以使用分区表避免某些特殊瓶颈，

可以备份和回复单个分区

分区的缺点及其限制

一个表最多只能有1024 个分区

如果分区字段中有主键或者唯一索引、那么所有主键和唯一索引列都必须包含进来。

分区表无法使用外键索引。

null  值会使分区过滤无效。

所有分区必须使用相同的存储引擎。

分区的类别：

RANGE 分区：基于一个连续区间的列值。把多行分区给分区

list 分区：

类似按照range  分区，区别在于分区时基于列值一个离散集合中的某个值

经行选择

hash 分区：基于用户定义的用户表达式返回值进行分区，该表达式使用

这些插入的列值进行计算（产生非负的任何表达式）

key 分区 类似hash 分区。






