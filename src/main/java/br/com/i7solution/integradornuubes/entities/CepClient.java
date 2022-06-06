package br.com.i7solution.integradornuubes.entities;

import br.com.i7solution.integradornuubes.entities.dtos.ClienteDTO;
import br.com.i7solution.integradornuubes.entities.dtos.ViaCepDTO;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class CepClient {

    public ViaCepDTO getDadosCep(String cep) throws IOException {
        var cepLimpo = cep.replace(".","").replace("-","");
        String url = "https://viacep.com.br/ws/" + cepLimpo + "/json";

        HttpResponse<ViaCepDTO> response = null;
        try {
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .asObject(ViaCepDTO.class);
            return response.getBody();
        } catch (UnirestException e) {
            log.warn("[getDadosCep]: Erro -> " + e.getMessage());
        }
        return null;
    }

}
