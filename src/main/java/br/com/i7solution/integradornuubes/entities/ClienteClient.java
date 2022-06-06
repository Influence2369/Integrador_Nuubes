package br.com.i7solution.integradornuubes.entities;

import br.com.i7solution.integradornuubes.config.PropertiesConfig;
import br.com.i7solution.integradornuubes.entities.dtos.ClienteDTO;
import br.com.i7solution.integradornuubes.entities.dtos.VinculosDTO;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Log4j2
@Service
public class ClienteClient {

    @Autowired
    private PropertiesConfig properties;

    public ClienteDTO getClientePorId(String id) throws IOException {
        var props = properties.getProperties();
        String idCliente = props.getProperty("properties.idcliente");
        String token = props.getProperty("properties.token");
        String url = DadosMicroservicos.endPointCliente;

        HttpResponse<ClienteDTO> response = null;
        try {
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .queryString("idClienteI7", idCliente)
                    .queryString("idProdutoI7", DadosMicroservicos.idProduto)
                    .asObject(ClienteDTO.class);
            return response.getBody();
        } catch (UnirestException e) {
            log.warn("[getClientePorId]: Erro -> " + e.getMessage());
        }
        return null;
    }

    public ClienteDTO getClientePorCpfCnpj(String cpfCnpj) throws IOException {
        var props = properties.getProperties();
        String idCliente = props.getProperty("properties.idcliente");
        String token = props.getProperty("properties.token");
        String url = DadosMicroservicos.endPointCliente;
        ClienteDTO result = new ClienteDTO();
        HttpResponse<ClienteDTO[]> response = null;
        try {
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .queryString("idClienteI7", idCliente)
                    .queryString("idProdutoI7", DadosMicroservicos.idProduto)
                    .queryString("cpfCnpj", cpfCnpj)
                    .asObject(ClienteDTO[].class);

            if (response.isSuccess()) {
                if (response.getBody().length > 0) {
                    result = response.getBody()[0];
                    result.setError(null);
                }
            } else {
                result.setError("Erro: " + response.getStatus() + " - " + response.getStatusText());
            }
        } catch (UnirestException e) {
            log.warn("[getClientePorCpfCnpj]: Erro -> " + e.getMessage());
        }
        return result;
    }

    public ClienteDTO[] getClientesPorDiasCadastro(Integer qtDiasCad) throws IOException {
        var props = properties.getProperties();
        String idCliente = props.getProperty("properties.idcliente");
        String token = props.getProperty("properties.token");
        String url = DadosMicroservicos.endPointCliente;
        HttpResponse<ClienteDTO[]> response = null;
        try {
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .queryString("idClienteI7", idCliente)
                    .queryString("idProdutoI7", DadosMicroservicos.idProduto)
                    .queryString("diasCadastro", qtDiasCad)
                    .connectTimeout(300000)
                    .asObject(ClienteDTO[].class);
        } catch (UnirestException e) {
            log.warn("Erro ao efetuar busca de dados no Winthor: " + e.getMessage());
        } catch (Exception e) {
            log.warn("Erro (getClientesPorDiasCadastro): " + e);
        }

        if (response != null) {
            //log.info("Response: " + response.getBody());
            return response.getBody();
        } else {
            log.info("Response nula");
            return null;
        }
    }

    public ClienteDTO postCliente(ClienteDTO dados) throws IOException {
        var props = properties.getProperties();
        String idCliente = props.getProperty("properties.idcliente");
        String token = props.getProperty("properties.token");
        String url = DadosMicroservicos.endPointCliente;

        HttpResponse<ClienteDTO> response = null;
        try {
        	
        	if(dados == null) {
                log.error("A função postCliente() está recebendo parâmetro null");
        		throw new Exception("A função postCliente() está recebendo parâmetro null");
        	}

            log.info("[postCliente] body: " + dados.toJson());
            response = Unirest.post(url)
                    .connectTimeout(60000)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .queryString("idClienteI7", idCliente)
                    .queryString("idProdutoI7", DadosMicroservicos.idProduto)
                    .body(dados.toJson())
                    .asObject(ClienteDTO.class);
        } catch (UnirestException e) {
            log.warn("[postCliente: UnirestException] - Erro -> " + e.getMessage());
        } catch (Exception e) {
            log.warn("[postCliente: Exception] - Erro -> " + e);
        }

        if (response != null) {
            if (response.isSuccess()) {
                return response.getBody();
            } else {
                log.warn("[postCliente] - Retorno: "
                        + "\n -> Status: " + response.getStatus() + "-" + response.getStatusText()
                        + "\n -> ParsingError: " + response.getParsingError().get()
                        + "\n -> Body: " + dados.toJson()
                );
            }
        }
        return null;
    }

    public VinculosDTO getVinculos() throws IOException {
        var props = properties.getProperties();
        String idCliente = props.getProperty("properties.idcliente");
        String url = DadosMicroservicos.endPointVinculos;
        HttpResponse<VinculosDTO[]> response = null;
        try {
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .queryString("idClienteI7", idCliente)
                    .queryString("idProdutoI7", DadosMicroservicos.idProduto)
                    .asObject(VinculosDTO[].class);
            if (response.getBody() != null) {
                if (response.getBody().length > 0) {
                    return response.getBody()[0];
                }
            }
        } catch (Exception e) {
            log.warn("[getVinculos] - Erro -> " + e.getMessage());
        }
        return null;
    }
}

