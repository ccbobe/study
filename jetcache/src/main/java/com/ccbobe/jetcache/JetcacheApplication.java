package com.ccbobe.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages ={"com.ccbobe.jetcache"})
public class JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(JetcacheApplication.class, args);
    }

}

