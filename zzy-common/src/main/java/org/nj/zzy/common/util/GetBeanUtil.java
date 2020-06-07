package org.nj.zzy.common.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Zhenyuan Zhang
 * @time 2020/6/7 23:31
 */
@Component
public class GetBeanUtil implements ApplicationContextAware {
    protected static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> c){
        return context.getBean(c);
    }
}
