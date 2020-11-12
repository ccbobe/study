package com.ccbobe;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ccbobe
 */
@Component
public class RunProcess  implements ApplicationContextAware {

    private ApplicationContext context;

    private ConcurrentHashMap<String, BaseRun> bases = new ConcurrentHashMap<>(32);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @PostConstruct
    public void  init(){
        context.getBeansWithAnnotation(RoutingClass.class).forEach((k,v)->{
            RoutingClass routingClass = v.getClass().getAnnotation(RoutingClass.class);
            if (BaseRun.class.isAssignableFrom(v.getClass())) {
                bases.put(routingClass.name(), (BaseRun ) v);
            }
        });
    }


    public  Context run(Context context){
        BaseRun baseRun = bases.get(context.getName());
        if (baseRun instanceof BaseBofore){
            Context before = ((BaseBofore) baseRun).before(context);
            context.setBefore(before.getBefore());
        }

        Context run = baseRun.run(context);
        context.setResult(run.getResult());
        if (baseRun instanceof BaseAfter){
            Context after = ((BaseAfter) baseRun).afterRun(context);
            context.setAfter(after.getAfter());
        }

        return context;
    }

}
