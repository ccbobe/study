package com.ccbobe;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ccbobe
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 向容器中动态添加class类
     * @param t
     * @param <T>
     * @return
     */
    public  static <T extends Base> T addT(String className){
        try {
            Class<?> loadClass = context.getClassLoader().loadClass(className);
            return (T) context.getBean(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  <T extends Base> T getBean(String className){
        return (T)context.getBean(className);
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
