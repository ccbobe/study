 内存泄漏是造成内存溢出的主要原因之一，在程序中当一个对象已经不需要再使用本该被回收时，另外一个正在使用的对象持有它的引用从而导致它不能被回收，本该被回收的对象不能被回收而停留在堆内存中，就产生了内存泄漏。
 简单记录下内存泄漏的分析步骤：（不对的望指正）

实例分析：

第一步：查看cpu最高的进程id

注：假设cpu最高的进程id为 java_pid

top -c

14151

第二步：查看进程所有线程的资源消耗情况，找到cpu负载高的线程id

注：假设cpu负载高的线程id为 thread_pid
top -H -p java_pid
top -H -p 14151
14153

第三步：查看当前java进程的堆栈信息，生成thread_dump文件
隔段时间再执行一次stack命令获取thread dump，区分两份dump是否有差别，在nid=0x246c的线程调用栈中，
发现该线程一直在执行JstackCase类第33行的calculate方法，得到这个信息，就可以检查对应的代码是否有问题。
jstack java_pid > dump_thread.log
jstack 14151 > dump_thread.log

第四步：将cpu负载高的线程id转成16进制的值
注：在thread dump中每个线程都有一个nid，找到对应的nid即可；
printf "%x\n" thread_pid
printf "%x\n" 14153
3749

基本执行过程：
第一步：获取Java进程pid（以及传递给JVM的参数）

jps -v
ps -ef | grep demo-web

第二步：打印java进程的堆空间概要（可以粗略的检验heap空间的使用情况。）
jmap -heap 12814


第三步：生成heap dump文件
jmap -dump:format=b,file=dump_heap.log 12814


第四步：生成heap live dump文件
jmap -dump:live,format=b,file=dump_heap_live.log 12814


第五步：生成thread dump文件
jstack pid >dump_thread.log

使用top -H -p 进程号查看异常线程
top -H -p 12814

找到cpu负载高的线程id，把这个数字转换成16进制
使用printf "%x\n" 线程号将异常线程号转化为16进制
printf "%x\n" 线程id
30c9

jstack pid|grep 30c9 -A90

第六步：使用 Eclipse Memory Analyzer（MAT） 进行堆转储文件分析
启动MAT，选择菜单项 File- Open Heap Dump 来加载需要分析的堆转储文件
Overview中查看Histogram和Leak Suspects面板
具体问题具体分析


