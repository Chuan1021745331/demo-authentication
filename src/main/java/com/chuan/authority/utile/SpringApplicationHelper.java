package com.chuan.authority.utile;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @类名:
 * @包名: com.chuan.authority.utile
 * @描述: (获取springApplication)
 * @日期: 2018/8/28 20:42
 */
@Component
@Lazy(false)
public class SpringApplicationHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext=null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        assertContextInjected();
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String beanName){
        assertContextInjected();
        return applicationContext.getBean(beanName);
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null,
                "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }
}
