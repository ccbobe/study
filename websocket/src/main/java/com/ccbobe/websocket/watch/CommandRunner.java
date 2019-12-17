package com.ccbobe.websocket.watch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ccbobe
 */
@Slf4j
@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    private FileMonitor fileMonitor;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public void run(String... args) throws Exception {
        log.info("开始监控指定文件");
        //指定监控文件目录
        Path path = Paths.get("C:\\data\\downland\\");

        //获取监控服务类
        final WatchService watchService = fileMonitor.getWatchService();
        //监听创建和删除事件
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE
                ,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);

        executorService.submit(()->{
            while (true) {
                WatchKey watchKey = null;
                try {
                    //阻塞操作
                    watchKey = watchService.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (WatchEvent event : watchKey.pollEvents()) {
                    WatchEvent.Kind eventKind = event.kind();
                    if (eventKind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    String fileName = event.context().toString();
                    log.info("当前文件变化{}==>{}",fileName,event.kind().name());

                    //相关业务擦做
                }
                //重置监控器进行下一个文件监控
                boolean isKeyValid = watchKey.reset();
                if (!isKeyValid) {
                    break;
                }
            }
        });
    }
}
