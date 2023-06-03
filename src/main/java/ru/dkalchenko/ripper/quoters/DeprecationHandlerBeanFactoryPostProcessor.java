package ru.dkalchenko.ripper.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.type.MethodMetadata;

public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                MethodMetadata factoryMethodMetadata = ((AnnotatedBeanDefinition) beanDefinition).getFactoryMethodMetadata();
                if (factoryMethodMetadata != null) {
                    String originalClassName = factoryMethodMetadata.getReturnTypeName();
                    try {
                        Class<?> beanClass = Class.forName(originalClassName);
                        DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);
                        if (annotation != null) {
                            beanDefinition.setBeanClassName(annotation.newImpl().getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}