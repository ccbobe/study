package com.ccbobe.websocket.watch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;

/**
 * @Author:ccbobe
 * 文件目录监控
 */
@Slf4j
@Service
public class FileMonitor implements InitializingBean, DisposableBean {

    private WatchService watchService;

    @Override
    public void destroy() throws Exception {
        log.info("文件监控器销毁.....");
        if (watchService!=null){
            watchService.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }

    public void init() throws IOException {
        log.info("文件监控器初始化.......");
        this.watchService = FileSystems.getDefault().newWatchService();
    }

    public WatchService getWatchService() {
        return watchService;
    }

    public void setWatchService(WatchService watchService) {
        this.watchService = watchService;
    }
}
