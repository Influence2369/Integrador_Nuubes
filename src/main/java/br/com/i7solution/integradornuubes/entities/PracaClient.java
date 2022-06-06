package br.com.i7solution.integradornuubes.entities;

import br.com.i7solution.integradornuubes.config.PropertiesConfig;
import br.com.i7solution.integradornuubes.entities.dtos.PracaDTO;
import br.com.i7solution.integradornuubes.entities.dtos.ViaCepDTO;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class PracaClient {

    @Autowired
    private PropertiesConfig properties;

    public PracaDTO getPraca(String cidade, String uf, String tipoFJ) throws IOException {
        var props = properties.getProperties();
        String idCliente = props.getProperty("properties.idcliente");
        String token = props.getProperty("properties.token");
        String url = DadosMicroservicos.endPointPracas;

        HttpResponse<PracaDTO[]> response = null;
        try {
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .queryString("idClienteI7", idCliente)
                    .queryString("idProdutoI7", DadosMicroservicos.idProduto)
                    .queryString("descricao", cidade + "%" + tipoFJ)
                    .queryString("uf", uf)
                    .asObject(PracaDTO[].class);

            if (response.isSuccess()) {
                if (response.getBody().length > 0) {
                    return response.getBody()[0];
                }
            }
        } catch (UnirestException e) {
            log.warn("[getPraca]: Erro -> " + e.getMessage());
        }
        return null;
    }

}
