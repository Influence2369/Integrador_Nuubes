package br.com.i7solution.integradornuubes.entities.dtos;

import br.com.i7solution.integradornuubes.tipos.TipoContato;
import br.com.i7solution.integradornuubes.tools.Ferramentas;

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
    private String dataNascimento;
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
                "  \"nome\": \"" + nome.replaceAll("\\r\n|\n", " ") + "\",\n" +
                "  \"idCliente\": \"" + idCliente + "\",\n" +
                "  \"cpfCnpj\": \"" + cpfCnpj + "\",\n" +
                "  \"tipoContato\": \"" + tipoContato + "\",\n" +
                "  \"rg\": \"" + rg + "\",\n" +
                "  \"dtnascimento\": \"" + (dataNascimento.substring(6) + "-" + dataNascimento.substring(3,5) + "-" + dataNascimento.substring(0,2) +"T00:00:00") + "\",\n" +
                "  \"endereco\": \"" + endereco.replaceAll("\\r\n|\n", " ") + "\",\n" +
                "  \"bairro\": \"" + bairro.replaceAll("\\r\n|\n", " ") + "\",\n" +
                "  \"cidade\": \"" + cidade + "\",\n" +
                "  \"estado\": \"" + estado + "\",\n" +
                "  \"cep\": \"" + cep + "\",\n" +
                "  \"telefone\": \"" + telefone + "\",\n" +
                "  \"celular\": \"" + celular + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"observacoes\": \"" + observacoes.replaceAll("\\r\n|\n", " ") + "\"\n" +
                "}";
    }
}
