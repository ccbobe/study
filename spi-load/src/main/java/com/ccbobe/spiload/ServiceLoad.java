package com.ccbobe.spiload;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.ServiceLoader;
@Component
public class ServiceLoad {

    @PostConstruct
    public void loadService(){
        ServiceLoader<MathService> load = ServiceLoader.load(MathService.class);
        Iterator<MathService> it = load.iterator();

        String name = "MathAddService";
        while(it.hasNext()){
            MathService mathService = it.next();
            System.out.println(mathService.add(10,3));
        }
    }
}
