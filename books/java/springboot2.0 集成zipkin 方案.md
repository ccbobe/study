springboot 2.0以上集成 ，推荐使用官方jar 
docker run -d -p 9411:9411 
-e STORAGE_TYPE=mysql 
-e MYSQL_HOST=221.216.12.26
-e MYSQL_TCP_PORT=3398
-e MYSQL_DB=dcc_zipkin 
-e MYSQL_USER=test
-e MYSQL_PASS=tn123456 
-e zipkin.collector.rabbitmq.addresses=47.93.44.58:5672 
-e zipkin.collector.rabbitmq.username=test
-e zipkin.collector.rabbitmq.password=test  openzipkin/zipkin