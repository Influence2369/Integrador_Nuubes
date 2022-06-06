package br.com.i7solution.integradornuubes.apinuubes;

import br.com.i7solution.integradornuubes.apinuubes.dtos.CustomerDTO;
import br.com.i7solution.integradornuubes.apinuubes.dtos.CustomerListDTO;
import br.com.i7solution.integradornuubes.config.PropertiesConfig;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
public class CustomerListClient {
    @Autowired
    private PropertiesConfig properties;

    public CustomerListDTO[] retornoClientes() throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointCustomer;
        HttpResponse<CustomerListDTO[]> response = null;
        try {
            response = Unirest.get(url).header("Content-Type", "application/json")
                    .queryString("company.key", customerKey)
                    .asObject(CustomerListDTO[].class);
            log.info(response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();

        }


        return null;
    }

    public CustomerListDTO[] postClienteData(CustomerDTO data) throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointCustomer;
        HttpResponse<CustomerListDTO[]> response = null;
        try {
            response = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .queryString("company.key", customerKey)
                    .body(data).asObject(CustomerListDTO[].class);
            log.info(response.getStatus());
            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
