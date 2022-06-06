package br.com.i7solution.integradornuubes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class PropertiesConfig {

    @Bean
    public Properties getProperties() throws IOException {
        Properties props = new ManipuladorProperties().getProp();
        return props;
    }
}