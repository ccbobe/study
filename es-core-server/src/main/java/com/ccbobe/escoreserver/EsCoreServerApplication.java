package com.ccbobe.escoreserver;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@RestController
@EnableElasticsearchRepositories
@SpringBootApplication
public class EsCoreServerApplication {



    public static void main(String[] args) {
        System.out.println("你好");
        SpringApplication.run(EsCoreServerApplication.class, args);

    }

    @Autowired
    private ProductRepository productRepository;


    //@PostConstruct
    public void run() throws Exception{
       /* IndicesClient indices = highLevelClient.indices();

        boolean exists = indices.exists(new GetIndexRequest("products"), RequestOptions.DEFAULT);
        System.out.println(exists);

        SearchRequest request = new SearchRequest();
        request.indices("products");

        SearchResponse search = highLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(search.getHits().iterator().next());*/

       /* Optional<Products> products = productRepository.findById(1);

        System.out.println(JSON.toJSONString(products.get()));*/

        //productRepository.deleteById("mMTOwXIBb1KKomS_R32s");


        List<Products> list = productRepository.queryProductsByNameLike("中国人的洗衣粉");


        List<Products> products = productRepository.queryProductsByNameLike("白猫");

        System.out.println(JSON.toJSONString(list));


        System.out.println(JSON.toJSONString(products));


        System.out.println("江西理工大学");
    }




    @Bean
    public SelfHealth selfHealth() {
        return new SelfHealth();
    }

    @RequestMapping("/")
    public String index(){
        System.out.println("你好");
        return "你好";
    }
}
