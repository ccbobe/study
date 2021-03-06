package com.ccbobe.common.config;

import com.hazelcast.config.*;
import com.hazelcast.config.cp.CPSubsystemConfig;
import com.hazelcast.config.cp.RaftAlgorithmConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ccbobe
 */
@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance(){
        return Hazelcast.newHazelcastInstance(config());
    }

    @Bean
    public Config config(){
        //默认配置信息
        Config config = new Config();
        //网络配置信息
        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setInterfaces(new InterfacesConfig().setEnabled(true).addInterface("192.168.0.*"));

        networkConfig.setInterfaces(new InterfacesConfig().addInterface("192.168.0.*").setEnabled(true));
        JoinConfig joinConfig = new JoinConfig();

        //广播协议关闭
        joinConfig.getMulticastConfig().setEnabled(false);
        //节点以tcp的方式加入
        joinConfig.setTcpIpConfig(new TcpIpConfig()
                .setRequiredMember("192.168.0.130")
                .addMember("192.168.0.*")
                .addMember("192.168.0.130:5701")
                .setEnabled(true));
        networkConfig.setJoin(joinConfig);
        config.setNetworkConfig(networkConfig);

        config
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("instruments")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(20000));

      config.addLockConfig(new LockConfig().setName("lock").setQuorumName("a"));

        config.addExecutorConfig(new ExecutorConfig().setName("exec").setPoolSize(10));

       config.addCountDownLatchConfig(new CountDownLatchConfig().setName("latch"));


        config.getCPSubsystemConfig().setCPMemberCount(3);

        config.addQueueConfig(new QueueConfig().setName("queue").setStatisticsEnabled(true));

        config.addQuorumConfig(new QuorumConfig().setName("Quorum").setEnabled(true));
        config.addLockConfig(new LockConfig().setName("lock"));

        config.addTopicConfig(new TopicConfig().setName("topic").setMultiThreadingEnabled(true).setStatisticsEnabled(true));



        config.addListConfig(new ListConfig()
                .setName("list").setMergePolicyConfig(
                        new MergePolicyConfig()
                                .setPolicy(MergePolicyConfig.DEFAULT_MERGE_POLICY)).setStatisticsEnabled(true));
        return config;
    }


}
