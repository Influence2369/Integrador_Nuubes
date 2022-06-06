package br.com.i7solution.integradornuubes.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class UnirestConfig {

    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper mapper;

    @PostConstruct
    public void postConstruct() {
        Unirest.config().setObjectMapper(new ObjectMapper() {

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
