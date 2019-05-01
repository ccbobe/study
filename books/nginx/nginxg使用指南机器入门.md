nginx 入门指南机器常用说明

nginx 启动、停止、重启命令

nginx 启动命令操作
sudo /usr/local/nginx/nginx (二进制文件绝对路径)

nginx 从容停止命令 等待所有请求结束关闭服务
ps -ef | grep nginx 
kill -QUIT nginx主进程号

nginx 快速停止命令，立即关闭NGINX 服务
ps -ef | grep nginx
kill -TEAM nginx主进程号

强制停止nginx 命令
kill -9 nginx 主进程号

直接使用命令操作服务
kill -(HUP|TERM|QUIT) cat /usr/local/nginx/nginx.pid

nginx重启命令

1. 简单，先关闭进程，修改配置文件信息后，重新启动进程
kill -QUIT cat /usr/local/nginx/nginx.pid
sudo /usr/local/nginx/nginx

2. 重新加载配置文件，不重启进程，不会停止处理请求
3. 平滑更新nginx 二进制文件，不会停止处理请求

平滑升级nginx
仍可以恢复旧的服务器：
发送 HUP 信号给旧的主进程 - 它将在不重载配置文件的情况下启动它的工作进程
发送 QUIT 信号给新的主进程，要求其从容关闭其工作进程
发送 TERM 信号给新的主进程，迫使其退出
如果因为某些原因新的工作进程不能退出，向其发送 KILL 信号

nginx 优化


nginx 使用hash表完成请求，快速处理

考虑到保存键及其值的hash表存储单元的大小不至于超出设定参数(hash bucket size)， 在启动和每次重新配置时，Nginx为hash表选择尽可能小的尺寸。

直到hash表超过参数(hash max size)的大小才重新进行选择. 对于大多数hash表都有指令来修改这些参数。例如，保存服务器名字的hash表是由指令

server_names_hash_max_size
server_names_hash_bucket_size
