**** CompletableFuture基本用法 
(1)异步计算:所谓异步计算就是实现其中一个无需等待被调用的函数的返回值而让操作继续执行的方法。
java中简单的将就是另外开启一个线程完成其中的部分计算，使调用者继续运行或者返回。而不需要等待计算结果

(2)Future 接口的局限性
Future接口可以构建异步应用，但依然有其局限性。它很难直接表述多个Future 结果之间的依赖性。实际开发中，我们经常需要达成以下目的：
将多个异步计算的结果合并成一个
等待Future集合中的所有任务都完成
Future完成事件（即，任务完成以后触发执行动作）
(3) CompletionStage
CompletionStage代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段
一个阶段的计算执行可以是一个Function，Consumer或者Runnable。比如：stage.thenApply(x -> square(x)).thenAccept(x -> System.out.print(x)).thenRun(() -> System.out.println())
一个阶段的执行可能是被单个阶段的完成触发，也可能是由多个阶段一起触发

(4)CompletableFuture提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，并且提供了函数式编程的能力，可以通过回调的方式处理计算结果，也提供了转换和组合 CompletableFuture 的方法。
它可能代表一个明确完成的Future，也有可能代表一个完成阶段（ CompletionStage ），它支持在计算完成以后触发一些函数或执行某些动作。
它实现了Future和CompletionStage接口.

实例
//异步编程   将上一阶输出当做下一阶输入进行消费计算
        CompletableFuture<Integer> completableFuture =CompletableFuture.supplyAsync(()->1);
        completableFuture.thenApply(i ->i+2 ).thenApply(i->i*i).whenComplete((r,e)->{
            System.out.println(r);
        });
        
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
future.thenApply(i->i.toUpperCase()).thenApply(i->i+" world".toUpperCase()).whenComplete((r,e)->System.out.print(r));
 
 thenApply相当于回调函数（callback）
 
 CompletableFuture 创建方法：
   supplyAsync(),runAsync()
   
 可以看到，thenAccept和thenRun都是无返回值的。如果说thenApply是不停的输入输出的进行生产，那么thenAccept和thenRun就是在进行消耗。它们是整个计算的最后两个阶段。
 同样是执行指定的动作，同样是消耗，二者也有区别：
 thenAccept接收上一阶段的输出作为本阶段的输入 　　
 thenRun根本不关心前一阶段的输出，根本不不关心前一阶段的计算结果，因为它不需要输入参数  
 
 thenCombine整合两个计算结果
 CompletableFuture<String> thenCombine = completableFuture1.thenApply(i -> i + " spring").thenCombine(CompletableFuture.completedFuture("  大学"), (s1, s2) -> s1 + s2);