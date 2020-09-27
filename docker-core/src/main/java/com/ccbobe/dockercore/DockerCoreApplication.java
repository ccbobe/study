package com.ccbobe.dockercore;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.command.SearchImagesCmd;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DockerCoreApplication {

    public static void main(String[] args) {
        connectServer();
        SpringApplication.run(DockerCoreApplication.class, args);
    }

    /**
     * 连接docker服务
     */
    public static void connectServer() {
        DockerClient client= DockerClientBuilder.getInstance("tcp://192.168.20.32:2375").build();
        Info info = client.infoCmd().exec();
        System.out.println("docker的环境信息如下：=================");
        System.out.println(info.getContainers());


        //搜索数据
        List<SearchItem> items = client.searchImagesCmd("java").exec();
        for (SearchItem item :items) {
            System.out.println(item.getName()+" : "+item.isOfficial());
        }


        List<Image> images = client.listImagesCmd().exec();
        for (Image image:
             images) {
            System.out.println(image.getRepoTags()[0]);
        }

        //重新启动容器
        client.restartContainerCmd("1d410081cea6").exec();


        //kill 服务
        client.killContainerCmd("1d410081cea6").exec();

       /* ExposedPort tcpPort = ExposedPort.tcp(6378);
        Ports portBindings = new Ports();
        portBindings.bind(tcpPort, Ports.Binding.bindPort(6379));
        client.createContainerCmd("redis:5.0").withExposedPorts(tcpPort)
                .withName("redis-server_2")
                .withPrivileged(false)
                .withRestartPolicy(RestartPolicy.alwaysRestart())
                .exec();*/
    }



}
