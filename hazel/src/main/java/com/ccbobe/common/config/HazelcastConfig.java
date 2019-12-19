package com.ccbobe.common.config;

import com.hazelcast.config.*;
import com.hazelcast.config.cp.CPSubsystemConfig;
import com.hazelcast.config.cp.RaftAlgorithmConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccbobe
 */
@Configuration
public class HazelcastConfig {

    @Bean
    public Config config(){
        //默认配置信息
        Config config = new Config();
        //网络配置信息
        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setInterfaces(new InterfacesConfig().addInterface("192.168.1.*").setEnabled(true));
        JoinConfig joinConfig = new JoinConfig();

        //广播协议关闭
        joinConfig.setMulticastConfig(new MulticastConfig().setEnabled(false));
        //节点以tcp的方式加入
        joinConfig.setTcpIpConfig(new TcpIpConfig()
                .setRequiredMember("192.168.1.143")
                .addMember("192.168.1.*")
                .setEnabled(true));
        networkConfig.setJoin(joinConfig);
        config.setNetworkConfig(networkConfig);
        config.setLiteMember(false);
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


        config.addListConfig(new ListConfig()
                .setName("list").setMergePolicyConfig(
                        new MergePolicyConfig()
                                .setPolicy(MergePolicyConfig.DEFAULT_MERGE_POLICY)).setStatisticsEnabled(true));
        return config;
    }


}
