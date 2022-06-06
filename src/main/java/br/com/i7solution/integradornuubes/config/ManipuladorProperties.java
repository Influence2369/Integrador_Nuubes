package br.com.i7solution.integradornuubes.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "properties")
public class ManipuladorProperties {

    @Value("${properties.idcliente}")
    private String idCliente;
    @Value("${properties.companykey}")
    private String companyKey;
    @Value("${properties.token}")
    private String token;

    public Properties getProp() throws IOException {
        Properties props = new Properties();
        var path = System.getProperty("user.dir");
        FileInputStream file = new FileInputStream(
                path + "/classes/application.properties"
        );
        props.load(file);
        return props;
    }
}