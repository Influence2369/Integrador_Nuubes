package br.com.i7solution.integradornuubes.apinuubes.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OccurrenceDTO implements Serializable {
    @JsonProperty("area")
    private String area;
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("numero")
    private Long numero;
    @JsonProperty("data")
    private String data;
    @JsonProperty("solicitante")
    private String solicitante;
    @JsonProperty("inicio")
    private String inicio;
    @JsonProperty("prazo")
    private String prazo;
    @JsonProperty("cnpj")
    private String cnpj;
    @JsonProperty("codigo_cliente")
    private String codigoCliente;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("custo_realizado")
    private Double custoRealizado;
    @JsonProperty("assunto")
    private String assunto;
    @JsonProperty("cliente")
    private String cliente;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("departamento")
    private String departamento;
    @JsonProperty("termino")
    private String termino;
    @JsonProperty("responsavel")
    private String responsavel;
    @JsonProperty("custo_previsto")
    private Double custoPrevisto;
    @JsonProperty("status")
    private String status;
}
