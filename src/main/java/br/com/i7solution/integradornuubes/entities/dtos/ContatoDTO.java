package br.com.i7solution.integradornuubes.entities.dtos;

import br.com.i7solution.integradornuubes.tipos.TipoContato;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContatoDTO implements Serializable {
    private String id;
    private String nome;
    private String idCliente;
    private String cpfCnpj;
    private TipoContato tipoContato;
    private String rg;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private String celular;
    private String email;
    private String observacoes;

    String toJson() {
        return "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"nome\": \"" + nome + "\",\n" +
                "  \"idCliente\": \"" + idCliente + "\",\n" +
                "  \"cpfCnpj\": \"" + cpfCnpj + "\",\n" +
                "  \"tipoContato\": \"" + tipoContato + "\",\n" +
                "  \"rg\": \"" + rg + "\",\n" +
                "  \"dataNascimento\": \"" + dataNascimento.toString() + "\",\n" +
                "  \"endereco\": \"" + endereco.replaceAll("\\r\n|\n", "") + "\",\n" +
                "  \"bairro\": \"" + bairro + "\",\n" +
                "  \"cidade\": \"" + cidade + "\",\n" +
                "  \"estado\": \"" + estado + "\",\n" +
                "  \"cep\": \"" + cep + "\",\n" +
                "  \"telefone\": \"" + telefone + "\",\n" +
                "  \"celular\": \"" + celular + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"observacoes\": \"" + observacoes + "\"\n" +
                "}";
    }
}
