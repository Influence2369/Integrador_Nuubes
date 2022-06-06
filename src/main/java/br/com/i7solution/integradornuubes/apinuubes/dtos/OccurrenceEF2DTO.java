package br.com.i7solution.integradornuubes.apinuubes.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OccurrenceEF2DTO implements Serializable {
    @JsonProperty("numero")
    private Long numero;
    @JsonProperty("NOME COMPLETO (SÓCIOS/PROPRIETÁRIO)")
    private String nome;
    @JsonProperty("CIDADE (SÓCIOS/PROPRIETÁRIO)")
    private String cidade;
    @JsonProperty("ESTADO (SÓCIOS/PROPRIETÁRIO)")
    private String estado;
    @JsonProperty("CEP (SÓCIOS/PROPRIETÁRIO)")
    private String cep;
    @JsonProperty("TELEFONE (SÓCIOS/PROPRIETÁRIO)")
    private String telefone1;
    @JsonProperty("CELULAR (SÓCIOS/PROPRIETÁRIO)")
    private String telefone2;
    @JsonProperty("E-MAIL (SÓCIOS/PROPRIETÁRIO)")
    private String email;
    @JsonProperty("NOME REFERÊNCIA PESSOAL (1)")
    private String nomeRef1;
    @JsonProperty("TELEFONE REFERÊNCIA PESSOAL (1)")
    private String telefoneRef1;
    @JsonProperty("NOME REFERÊNCIA PESSOAL (2)")
    private String nomeRef2;
    @JsonProperty("TELEFONE REFERÊNCIA PESSOAL (2)")
    private String telefoneRef2;
    @JsonProperty("MODELO, TAMANHO DO FREEZER OU PRÓPRIO.")
    private String modelo;
}
