package br.com.i7solution.integradornuubes.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinculosDTO implements Serializable {
    private String idCliente;
    private String idProduto;
    private String baseUrl;
    private String erro;
}
