**docker常用命令使用及其说明**

***docker run :创建一个新的容器并运行命令***

docker run [OPTIONS] IMAGE [COMMAND] [ARG...]

-a stdin : 指定标准输入输出内容类型，可选项 Stdin /stdout/ stderr 三项。
-d: 后台运行进程，并返回容器ID.
-i: 以交互模式运行容器，通常与-t 同时使用。
-p: 端口映射，格式为：主机端口：容器端口。
-t: 为容器重新分派一个伪输入终端。通常与-i同时使用。
--name: 为容器指定一个名称。
--dns 8.8.8.8 ： 指定容器的使用DNS服务器，默认和宿主机器一致。
--dns-search example.com :指定容器的DNS 搜索域名，默认和宿主机器一致。
-h "mars" 指定容器的hostname 
-e username="richie" 设置环境变量；
--env-file=[] 从指定文件中读取环境变量。
--cpuset ="0-2" or --cpuset="0,1,2" 绑定容器的指定CPU 运行；
-m ： 设置容器使用内存的最大值。
--net="bridge": 指定容器的网络连接类型，支持的类型有： bridge/host/none/container

--link=[] :添加连接到另一个容器。
--expose=[] : 开放一组端口或者一个端口。

实例：
使用docker镜像nginx:latest 以后台模式启动一个容器，并将容器命令为mynginx.
`docker run --name mynginx -d nginx:latest`

使用镜像 nginx:latest 以后台模式启动一个容器，并将容器的端口80 端口映射到主机随机端口。

`docker run -P -d  nginx:latest`

使用镜像nginx:latest ,以后台模式启动一个容器，将容器的80 端口映射到宿主机器的80端口，主机的目录/data 映射到容器的 /data.

`docker run -p 80:80 -v /data:/data -d nginx: latest`

绑定容器的 8080 端口 并将其映射到本地机器127.0.0.1 的 80 端口上。

`docker run -p 127.0.0.1:80 :8080/tcp ubuntu bash`

使用镜像nginx:latest 以交互模式启动一个容器，在容器内执行/bin/bash 命令。

`docker run -it nginx:latest /bin/bash`

***docker exec: 在运行的容器中执行命令。***

docker exec [OPTIONS] contatiner command [args ....]

-d ： 分离模式在后台运行

-i: 即使没有附加也要保持stdin打开

-t: 分配一个伪终端。



在容器mynginx  中以交互模式执行容器内 /root/runoob.sh 脚本

`docker exec  -it mynginx /bin/sh /root/runoob.sh` 



docker start /stop / restart命令

docker start 启动一个或者多个已经停止的容器。

docker stop 停止一个运行的容器

docker restart 重启容器

实例

启动已经被停止的容器

`docker start myrunoob`

停止运行中的容器myrunoob

`docker stop myrunoob`

重新启动容器

`docker restart myrunoob`



docker  kill :杀掉一个正在运行的容器

docker kill [options] contatiner [ contatiner ]

-s 向容器发送一个信号

杀掉容器中的mynginx

docker kill -s KILL mynginx

docker rm : 删除一个或多个容器

docker rm [options] contatner [coontatner]

-f 通过sigkill 信号强制删除一个运行中的容器

-l :移除容器间的网络连接，而非容器本身

-v : -v 删除与容器关联的卷

实例

强制删除容器db01 db02

`docker rm -f db01 db02`

移除容器nginx01 对容器的db01 的连接 db

`docker rm -l db`

删除容器nginx01 ,并删除容器挂载的数据卷

`docker rm -v nginx01`

`docker pause/uppause 命令`

docker pause 暂停容器中的所有进行。

docker unpause ：恢复容器中所有的进程

docker pause [options] contatner [contatner ....]

docker unpause [options] contatner [contatner ...]

暂停数据库容器db01 提供服务。

`docker pause db01`

恢复数据库容器db01 提供的服务

`docker unpause db01`

docker create 命令

docker ： 创建一个新的容器但不启动它。

用法同 docker run 

docker create [options] image [command ] [arg ....]

实例

使用docker镜像nginx:latest 创建一个容器，并将容器命名为myrunoob

docker create --name myrunoob nginx:latest

docker ps 命令

docker ps

docker ps -a: 显示所有容器，包括未运行的。

docker ps -f: 根据条件过滤显示的容器。

docker --format: 指定返回值的模板文件。

docker -I :显示最近创建的容器。

docker -n: 列出最近创建的容器。

docker -no-tunc: 不截断输出。

docker -q : 静默模式，只显示容器编号。

列出所有运行的容器信息。

`docker ps`

列出最近创建的五个容器

`docker ps -n 5`

列出所有创建的容器ID.

`docker ps -a -q`



docker inspect 命令

获取镜像容器元数据。

docker inspect [options] name| ID [name|ID ...]

-f ： 指定返回值的模板文件。

-s : 显示总的文件大小。

--type:为指定类型的json.

docker top 命令

docker top 查看容器运行的进程信息 支持ps 命令参数

docker top [options] contatner [ ps options]

查看容器mymysql 的进程信息

docker top mymysql

docker attach 命令

docker attach 连接到正在运行的容器。

docker attach [options] contatner

要attach 上去的容器必须运行，可以同时连接用一个 catatiner 来共享屏幕。

如果container当前正在前台运行进程，如输出nginx的access.log日志，

CTRL-C不仅会导致退出容器，而且还stop了。这不是我们想要的，

detach的意思按理应该是脱离容器终端，

但容器依然运行。好在attach是可以带上--sig-proxy=false

来确保CTRL-D或CTRL-C不会关闭容器。

容器mynginx 将访问日志指定到标准输出，连接到容器查看访问信息。

docker attach --sig-proxy=false mynginx

docker events   从服务器获取实时事件。

docker events [options]

-f 根据条件过滤事件，

--since: 从指定时间戳后所显示的所有事件。

--until:流水时间显示到指定的时间为止。

docker logs 获取容器的日志

docker logs [options] contatner

-f 跟踪日志输出。

--since :显示某个开始时间的所有日志。

-t : 显示时间戳。

--tail: 近列出最新N 条容器日志。

docker wait 命令：阻塞运行直到容器停止，然后打印出他的推出代码。

`docker wait contatner`

docker export : 将文件系统作为一个tar 归档文件导出到stdout.

-o 将输入内容写入到文件。

docker port  命令

docker port： 列出指定的容器的端口映射，或者查找到private_port nat  再到面向公众的端口。

`docker port mysql`

## `容器rootfs 命令`

docker commit :从容器创建一个新的镜像。

docker commit [options] contatner   [REPOSITORY[:TAG]]



-a: 提交镜像的作者

-c:使用dockerFIle指令来创建镜像

-m : 提交时的文字说明

-p: 再commit 时，将容器暂停

将容器将容器a404c6c174a2 保存为新的镜像,并添加提交人信息和说明信息。

`docker commit -a "ccbobe" -m "my docker "  a404c6c174a2   mymysql:v2`

docker cp 命令

docker cp: 用与容器与主机之间的数据拷贝。

docker cp 【options】 contatner :SC_path DEST_PATH | -

docker cp [options] src_path |- contatner :dest_path

-L :保持源目标中的连接。

将主机/www/runoob目录拷贝到容器96f7f14e99ab的/www目录下。

`docker cp /www/runoob/  96f7f14e99ab:/www/`

将主机/www/runoob/ 的目录拷贝到容器96f7f14e99ab中，目录重命名为www。

`docker cp   /www/runoob 96f7f14e99ab:/www`

将容器96f7f14e99ab的/www目录拷贝到主机的/tmp目录中。

```dockerfile
docker cp  96f7f14e99ab:/www /tmp/
```

# Docker diff 命令

**docker diff :** 检查容器里文件结构的更改。



镜像仓库相关命令

`login pull push search`

docker login -u 登陆用户名 -p 登陆密码

docker logout 

docker pull 命令

docker pull 从镜像中拉取或者更新指定镜像

docker pull [options] name[:tag|@DIGEST]

-a 拉去所有taged  镜像

--disable-content-trust : 忽略镜像的校验，默认开启

从docker hub 中下载最新的镜像

docker  pull java 

从Docker Hub下载REPOSITORY为java的所有镜像

docker pull -a java 

docker push 命令

docker push [options] name[:tag]

**--disable-content-trust :**忽略镜像的校验,默认开启

上传本地镜像myapache:v1 到镜像仓库中。

`docker push myapache:v1`

### 本地镜像管理

images 

**docker images :** 列出本地镜像。

docker images [options] [repostory[:tag]]

-a 列出本地所有镜像

-digests: 显示镜像的摘要信息

-f:显示满足条件的镜像

--format :指定返回值的模板文件

--no-trunc: 显示完整的镜像信息

-q:只显示镜像ID

rmi 

docker rmi :删除一个或者多个镜像

-f 强制删除

-no-prune: 不移除该镜像的过程镜像，默认移除；

docker rmi -f runoob/ubantu:v4



tag



build



histroy



save



import



info/version

info





version

