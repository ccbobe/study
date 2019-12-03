
springboot kafka 集成注意事项

监听Topic中指定的分区
@KafkaListener注解的topicPartitions属性监听不同的partition分区。
@TopicPartition：topic--需要监听的Topic的名称，partitions --需要监听Topic的分区id，
partitionOffsets --可以设置从某个偏移量开始监听
@PartitionOffset：partition --分区Id，非数组，initialOffset --初始偏移量

