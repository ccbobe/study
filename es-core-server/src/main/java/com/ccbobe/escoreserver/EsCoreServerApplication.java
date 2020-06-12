package com.ccbobe.escoreserver;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.system.DiskSpaceHealthIndicatorProperties;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EsCoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsCoreServerApplication.class, args);
    }


    @Autowired
    private RestHighLevelClient client;


    public void run() throws Exception{
        CreateIndexRequest request = new CreateIndexRequest("search");
        client.indices().create(request, RequestOptions.DEFAULT);
    }


    @Bean
    @ConditionalOnExpression(
            value = "'true'.equals(environment.getProperty('self.check'))"
    )
    public SelfHealth selfHealth() {
        return new SelfHealth();
    }
}
