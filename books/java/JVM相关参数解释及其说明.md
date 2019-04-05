### **JVM常见的参数说明及其配置**

`-Xms20M` :表示设置JVM启动内存的最小值为20M，必须以M为单位.

`-Xmx20M`:表示设置JVM启动内存的最大值为20M，必须以M为单位。将-Xmx和-Xms设置为一样可以避免JVM内存自动扩展。Xmx和-Xms一般都要设置到10G、20G甚至还要高.

-verbose:gc  表示输出虚拟机中GC的详细情况

-Xss128k 表示可以设置虚拟机栈的大小为128k

-Xoss128k 表示设置本地方法栈的大小为128k。不过HotSpot并不区分虚拟机栈和本地方法栈，因此对于HotSpot来说这个参数是无效的

-XX:PermSize=10M
表示JVM初始分配的永久代（方法区）的容量，必须以M为单位

-XX:MaxPermSize=10M
表示JVM允许分配的永久代（方法区）的最大容量，必须以M为单位，大部分情况下这个参数默认为64M

-Xnoclassgc  表示关闭JVM对类的垃圾回收

-XX:+TraceClassLoading  表示查看类的加载信息

-XX:+TraceClassUnLoading 表示查看类的卸载信息

-XX:NewRatio=4 表示设置 年轻代（包括Eden和两个Survivor区）/老年代 的大小比值为1：4，这意味着年轻代占整个堆的1/5

-XX:SurvivorRatio=8  表示设置2个Survivor区：1个Eden区的大小比值为2:8，这意味着Survivor区占整个年轻代的1/5，这个参数默认为8

-Xmn20M  表示设置年轻代的大小为20M

-XX:+HeapDumpOnOutOfMemoryError 表示可以让虚拟机在出现内存溢出异常时Dump出当前的堆内存转储快照

-XX:+UseG1GC 表示让JVM使用G1垃圾收集器

-XX:+PrintGCDetails 表示在控制台上打印出GC具体细节

-XX:+PrintGC 表示在控制台上打印出GC信息

-XX:PretenureSizeThreshold=3145728 表示对象大于3145728（3M）时直接进入老年代分配，这里只能以字节作为单位.

-XX:CompileThreshold=1000 表示一个方法被调用1000次之后，会被认为是热点代码，并触发即时编译

-XX:+PrintHeapAtGC 表示可以看到每次GC前后堆内存布局

-XX:+UseSpining 开启自旋锁

-XX:PreBlockSpin 更改自旋锁的自旋次数，使用这个参数必须先开启自旋锁

-XX:+UseSerialGC
表示使用jvm的串行垃圾回收机制，该机制适用于丹cpu的环境下

-XX:+UseParallelGC
表示使用jvm的并行垃圾回收机制，该机制适合用于多cpu机制，同时对响应时间无强硬要求的环境下，使用-XX:ParallelGCThreads=设置并行垃圾回收的线程数，此值可以设置与机器处理器数量相等。

-XX:+UseParallelOldGC 表示年老代使用并行的垃圾回收机制

-XX:+UseConcMarkSweepGC
表示使用并发模式的垃圾回收机制，该模式适用于对响应时间要求高，具有多cpu的环境下