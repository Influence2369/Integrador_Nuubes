package br.com.i7solution.integradornuubes.apinuubes;

import br.com.i7solution.integradornuubes.apinuubes.dtos.OccurrenceDTO;
import br.com.i7solution.integradornuubes.apinuubes.dtos.OccurrenceEF2DTO;
import br.com.i7solution.integradornuubes.apinuubes.dtos.OccurrenceEFDTO;
import br.com.i7solution.integradornuubes.config.PropertiesConfig;
import kong.unirest.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
public class OccurrenceClient {

    @Autowired
    private PropertiesConfig properties;

    public OccurrenceDTO[] getOcorrenciasPorPeriodo(String dataIni, String dataFim) throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointOccurrence;
        try {
            var response = Unirest.get(url)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Accept", "*/*")
                    .queryString("company.key", customerKey)
                    .queryString("filter.dateAddIni", dataIni)
                    .queryString("filter.dateAddEnd", dataFim)
                    .connectTimeout(60000)
                    .asObject(OccurrenceDTO[].class);

            if (response != null) {
                var retorno =  response.getBody();
                return retorno;
            } else {
                return null;
            }
        } catch (UnirestException e) {
            if (!e.getMessage().contains("No content to map due to end-of-input")) {
                log.warn("[getOcorrenciasPorPeriodo]: Erro (getOcorrenciasPorPeriodo: UnirestException): " + e.getMessage());
            } else {
                return null;
            }
        } catch (Exception e) {
            log.warn("[getOcorrenciasPorPeriodo]: Erro (getOcorrenciasPorPeriodo: Exception): " + e);
            return null;
        }
        return null;
    }

    public OccurrenceEFDTO[] getOcorrenciasExtraFields(Long numero) throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointOccurrenceExtraFields;
        HttpResponse<OccurrenceEFDTO[]> response;
        try {

            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Accept", "*/*")
                    .queryString("company.key", customerKey)
                    .queryString("occurrence.numberOccurrence", numero)
                    .asObject(OccurrenceEFDTO[].class);

            if (response != null) {
                //log.info("[getOcorrenciasPorPeriodo]: " + response.getBody().toString());
                //return response.getBody();
                return response.getBody();
            } else {
                return null;
            }
        } catch (UnirestException e) {
            if (!e.getMessage().contains("No content to map due to end-of-input")) {
                log.warn("[getOcorrenciasExtraFields]: Erro (getOcorrenciasPorPeriodo: UnirestException): " + e.getMessage());
            } else {
                return null;
            }
        } catch (Exception e) {
            log.warn("[getOcorrenciasExtraFields]: Erro (getOcorrenciasPorPeriodo: Exception): " + e);
            return null;
        }
        return null;
    }

    public OccurrenceEF2DTO[] getOcorrenciasExtraFields2(Long numero) throws IOException {
        var prop = properties.getProperties();
        String customerKey = prop.getProperty("properties.companykey");
        String url = DadosNuubes.baseUrl + DadosNuubes.endPointOccurrenceExtraFields2;
        HttpResponse<OccurrenceEF2DTO[]> response;
        try {

            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Accept", "*/*")
                    .queryString("company.key", customerKey)
                    .queryString("occurrence.numberOccurrence", numero)
                    .asObject(OccurrenceEF2DTO[].class);

            if (response != null) {
                //log.info("[getOcorrenciasPorPeriodo]: " + response.getBody().toString());
                //return response.getBody();
                return response.getBody();
            } else {
                return null;
            }
        } catch (UnirestException e) {
            if (!e.getMessage().contains("No content to map due to end-of-input")) {
                log.warn("[getOcorrenciasExtraFields2]: Erro (getOcorrenciasPorPeriodo: UnirestException): " + e.getMessage());
            } else {
                return null;
            }
        } catch (Exception e) {
            log.warn("[getOcorrenciasExtraFields2]: Erro (getOcorrenciasPorPeriodo: Exception): " + e);
            return null;
        }
        return null;
    }

}
