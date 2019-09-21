package com.bertzzie.devex.graal.configurations;

import com.bertzzie.devex.graal.polyglot.GraalVMJavascript;
import com.bertzzie.devex.graal.polyglot.GraalVMR;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class BeanConfigurations {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public GraalVMR r(ResourceLoader loader) {
        return new GraalVMR(loader);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public GraalVMJavascript javascript(ResourceLoader loader) {
        return new GraalVMJavascript(loader);
    }
}
