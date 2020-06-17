package com.ccbobe.escoreserver;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@EnableElasticsearchRepositories
@SpringBootApplication
public class EsCoreServerApplication {



    public static void main(String[] args) {
        SpringApplication.run(EsCoreServerApplication.class, args);

    }

    @Autowired
    private ProductRepository productRepository;


    @PostConstruct
    public void run() throws Exception{
       /* IndicesClient indices = highLevelClient.indices();

        boolean exists = indices.exists(new GetIndexRequest("products"), RequestOptions.DEFAULT);
        System.out.println(exists);

        SearchRequest request = new SearchRequest();
        request.indices("products");

        SearchResponse search = highLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(search.getHits().iterator().next());*/

        Optional<Products> products = productRepository.findById(1);

        System.out.println(JSON.toJSONString(products.get()));

        Products build = Products.builder().id(6).count(200).name("白猫洗衣房").price(24.87).tag(Arrays.asList("洗衣房", "假冒产品")).build();


        productRepository.save(build);


        productRepository.deleteById("mMTOwXIBb1KKomS_R32s");

    }




    @Bean
    public SelfHealth selfHealth() {
        return new SelfHealth();
    }
}
