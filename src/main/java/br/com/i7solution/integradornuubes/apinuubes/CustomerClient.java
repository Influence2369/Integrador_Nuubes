package br.com.i7solution.integradornuubes.apinuubes;

import br.com.i7solution.integradornuubes.apinuubes.dtos.CustomerDTO;
import br.com.i7solution.integradornuubes.config.PropertiesConfig;
import br.com.i7solution.integradornuubes.tools.Ferramentas;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
public class CustomerClient {

    @Autowired
    private PropertiesConfig properties;

    public CustomerDTO[] getClientePorCnpj(String cnpj, String tipo) throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointCustomer;
        HttpResponse<CustomerDTO[]> response;
        try {
            var cnpjFormatado = Ferramentas.formatarCpfCnpj(cnpj, tipo);

            if (tipo.equalsIgnoreCase("F")) {
                response = Unirest.get(url)
                        .header("Content-Type", "application/json")
                        .header("Accept", "*/*")
                        .queryString("company.key", customerKey)
                        .queryString("customer.cpf", cnpjFormatado)
                        .asObject(CustomerDTO[].class);
            } else {
                response = Unirest.get(url)
                        .header("Content-Type", "application/json")
                        .header("Accept", "*/*")
                        .queryString("company.key", customerKey)
                        .queryString("customer.cnpj", cnpjFormatado)
                        .asObject(CustomerDTO[].class);
            }
            if (response != null) {
                log.info("Response nuubes.getClientePorCnpj: " + response.getBody().toString());
                return response.getBody();
            } else {
                return null;
            }
        } catch (UnirestException e) {
            if (!e.getMessage().contains("No content to map due to end-of-input")) {
                log.warn("Erro (getClientePorCnpj: UnirestException): " + e.getMessage());
            } else {
                return null;
            }
        } catch (Exception e) {
            log.warn("Erro (getClientePorCnpj: Exception): " + e);
            return null;
        }
        return null;
    }

    public boolean existeCliente(String cnpj, String tipo) throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        log.info("[existeCliente] -> cnpj: " + cnpj + " tipo: " + tipo);
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointCustomer;
        HttpResponse<CustomerDTO[]> response;
        try {
            var cnpjFormatado = Ferramentas.formatarCpfCnpj(cnpj, tipo);
            var tipoCPFCNPJ = "";
            if (tipo.equalsIgnoreCase("F")) {
                tipoCPFCNPJ = "customer.cpf";
            } else {
                tipoCPFCNPJ = "customer.cnpj";
            }

            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Accept", "*/*")
                    .queryString("company.key", customerKey)
                    .queryString(tipoCPFCNPJ, cnpjFormatado)
                    .asObject(CustomerDTO[].class);
            if (response != null) {
                log.info("Response nuubes.getClientePorCnpj: " + response.getBody().toString());
                return true;
            } else {
                return false;
            }
        } catch (UnirestException e) {
            if (!e.getMessage().contains("No content to map due to end-of-input")) {
                log.warn("Erro (getClientePorCnpj: UnirestException): " + e.getMessage());
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("Erro (getClientePorCnpj: Exception): " + e);
            return false;
        }
        return false;
    }

    public String postCliente(CustomerDTO data) throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointCustomer;
        HttpResponse<String> response = null;
        try {
            if (data.getCpf() != null) {
                response = Unirest.post(url)
                        .header("Content-Type", "application/json")
                        .queryString("company.key", customerKey)
                        .queryString("customer.cpf", data.getCpf())
                        .queryString("customer.externalCode", data.getExternalCode())
                        .queryString("customer.name", Ferramentas.removerAcentos(data.getName()))
                        .queryString("customer.fantasyName", Ferramentas.removerAcentos(data.getFantasyName()))
                        .queryString("customer.tribute.name", Ferramentas.removerAcentos(data.getTribute()))
                        .queryString("customer.street", Ferramentas.removerAcentos(data.getStreet()))
                        .queryString("customer.number", Ferramentas.removerAcentos(data.getNumber()))
                        .queryString("customer.complement", Ferramentas.removerAcentos(data.getComplement()))
                        .queryString("customer.district", Ferramentas.removerAcentos(data.getDistrict()))
                        .queryString("customer.city", Ferramentas.removerAcentos(data.getCity()))
                        .queryString("customer.cep", data.getCep())
                        .queryString("customer.uf", Ferramentas.removerAcentos(data.getUf()))
                        .asString();
            } else {
                response = Unirest.post(url)
                        .header("Content-Type", "application/json")
                        .queryString("company.key", customerKey)
                        .queryString("customer.cnpj", data.getCnpj())
                        .queryString("customer.externalCode", data.getExternalCode())
                        .queryString("customer.name", Ferramentas.removerAcentos(data.getName()))
                        .queryString("customer.fantasyName", Ferramentas.removerAcentos(data.getFantasyName()))
                        .queryString("customer.tribute.name", Ferramentas.removerAcentos(data.getTribute()))
                        .queryString("customer.street", Ferramentas.removerAcentos(data.getStreet()))
                        .queryString("customer.number", Ferramentas.removerAcentos(data.getNumber()))
                        .queryString("customer.complement", Ferramentas.removerAcentos(data.getComplement()))
                        .queryString("customer.district", Ferramentas.removerAcentos(data.getDistrict()))
                        .queryString("customer.city", Ferramentas.removerAcentos(data.getCity()))
                        .queryString("customer.cep", data.getCep())
                        .queryString("customer.uf", Ferramentas.removerAcentos(data.getUf()))
                        .asString();
            }
        } catch (Exception e) {
            log.warn("Erro ao efetuar o cadastro do cliente na API Nuubes(postCliente): " + e);
        }
        if (response != null) {
            return response.getBody();
        }
        return null;
    }

}
