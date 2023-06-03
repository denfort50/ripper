package ru.dkalchenko.ripper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dkalchenko.ripper.quoters.*;

@Configuration
public class SpringConfig {

    @Bean
    public InjectRandomInitAnnotationBeanPostProcessor beanPostProcessor() {
        return new InjectRandomInitAnnotationBeanPostProcessor();
    }

    @Bean
    public DeprecationHandlerBeanFactoryPostProcessor deprecationHandlerBeanFactoryPostProcessor() {
        return new DeprecationHandlerBeanFactoryPostProcessor();
    }

    @Bean
    public ProfilingHandlerBeanPostProcessor profilingHandlerBeanPostProcessor() throws Exception {
        return new ProfilingHandlerBeanPostProcessor();
    }

    @Bean
    public PostProxyInvokerContextListener postProxyInvokerContextListener() {
        return new PostProxyInvokerContextListener();
    }

    @Bean(name = "terminatorQuoter")
    public TerminatorQuoter terminatorQuoter() {
        TerminatorQuoter terminatorQuoter = new TerminatorQuoter();
        terminatorQuoter.setMessage("I'll be back");
        return terminatorQuoter;
    }
}
