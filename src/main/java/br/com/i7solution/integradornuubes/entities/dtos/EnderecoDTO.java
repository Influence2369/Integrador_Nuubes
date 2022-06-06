package br.com.i7solution.integradornuubes.entities.dtos;

import br.com.i7solution.integradornuubes.tipos.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO implements Serializable {
    private String id;
    private String idCliente;
    private String numero;
    private String cep;
    private String bairro;
    private String complemento;
    private String pontoRefer; //PONTOREFER
    private String uf;
    private String cidade;
    private Long codigoIbge;
    private Long codigoPais;
    private String rua;
    private TipoEndereco tipoEndereco;

    public String toJson() {
        return "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"idCliente\": \"" + idCliente + "\",\n" +
                "  \"numero\": \"" + numero + "\",\n" +
                "  \"cep\": \"" + cep + "\",\n" +
                "  \"bairro\": \"" + bairro + "\",\n" +
                "  \"complemento\": \"" + complemento + "\",\n" +
                "  \"pontoRefer\": \"" + pontoRefer + "\",\n" +
                "  \"uf\": \"" + uf + "\",\n" +
                "  \"cidade\": \"" + cidade + "\",\n" +
                "  \"codigoIbge\": \"" + codigoIbge + "\",\n" +
                "  \"codigoPais\": \"" + codigoPais + "\",\n" +
                "  \"rua\": \"" + rua + "\",\n" +
                "  \"tipoEndereco\": \"" + tipoEndereco + "\"\n" +
               "}";
    }
}
