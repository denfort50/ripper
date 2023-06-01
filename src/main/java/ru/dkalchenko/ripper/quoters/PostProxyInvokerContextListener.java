package ru.dkalchenko.ripper.quoters;


import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.MethodMetadata;

import java.lang.reflect.Method;

public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                MethodMetadata factoryMethodMetadata = ((AnnotatedBeanDefinition) beanDefinition).getFactoryMethodMetadata();
                if (factoryMethodMetadata != null) {
                    String originalClassName = factoryMethodMetadata.getReturnTypeName();
                    try {
                        Class<?> originalClass = Class.forName(originalClassName);
                        Method[] methods = originalClass.getMethods();
                        for (Method method : methods) {
                            if (method.isAnnotationPresent(PostProxy.class)) {
                                Object bean = context.getBean(name);
                                Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                                currentMethod.invoke(bean);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
