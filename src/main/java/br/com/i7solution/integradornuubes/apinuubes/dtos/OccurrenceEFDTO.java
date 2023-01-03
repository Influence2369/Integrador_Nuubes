package br.com.i7solution.integradornuubes.apinuubes.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OccurrenceEFDTO implements Serializable {
    @JsonProperty("INSC. ESTADUAL")
    private String inscEstadual;
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("numero")
    private Long numero;
    @JsonProperty("data")
    private String data;
    @JsonProperty("inicio")
    private String inicio;
    @JsonProperty("ÓRGÃO EXPEDITOR")
    private String orgaoExp;
    @JsonProperty("prazo")
    private String prazo;
    @JsonProperty("cnpj")
    private String cnpj;
    @JsonProperty("TELEFONE (1)")
    private String telefone1;
    @JsonProperty("codigo_cliente")
    private String codigoCliente;
    @JsonProperty("CNPJ/CPF")
    private String cpfCnpj;
    @JsonProperty("assunto")
    private String assunto;
    @JsonProperty("ENDEREÇO  - RUA/AV (SÓCIOS/PROPRIETÁRIO)")
    private String endereco;
    @JsonProperty("ESTADO")
    private String estado;
    @JsonProperty("TIPO")
    private String tipoFJ;
    @JsonProperty("ESTADO CIVIL")
    private String estCivil;
//    "cpf": "",
//    private String cpf;
    @JsonProperty("termino")
    private String termino;
    @JsonProperty("COMPLEMENTO (SÓCIOS/PROPRIETÁRIO)")
    private String complemento;
    @JsonProperty("responsavel")
    private String responsavel;
    @JsonProperty("custo_previsto")
    private Double custoPrevisto;
    @JsonProperty("CIDADE")
    private String cidade;
    @JsonProperty("NÚMERO")
    private String nrEndereco;
    @JsonProperty("COMPLEMENTO / PONTO DE REFERÊNCIA")
    private String obsEntrega;
    @JsonProperty("NÚMERO DA CASA (SÓCIOS/PROPRIETÁRIO)")
    private String nrCasaEndereco;
    @JsonProperty("area")
    private String area;
    @JsonProperty("E-MAIL")
    private String email;
    @JsonProperty("solicitante")
    private String solicitante;
    @JsonProperty("TELEFONE (2)")
    private String telefone2;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("custo_realizado")
    private Double custoRealizado;
    @JsonProperty("CEP")
    private String cep;
    @JsonProperty("cliente")
    private String cliente;
    @JsonProperty("NOME FANTASIA")
    private String fantasia;
    @JsonProperty("BAIRRO")
    private String bairro;
    @JsonProperty("RG")
    private String rg;
    @JsonProperty("BAIRRO (SÓCIOS/PROPRIETÁRIO)")
    private String bairroEndereco;
    @JsonProperty("CPF")
    private String cpf;
    @JsonProperty("departamento")
    private String departamento;
    @JsonProperty("DATA NASCIMENTO")
    private String dtNascimento;
    @JsonProperty("NOME COMPLETO (SÓCIOS/PROPRIETÁRIO)")
    private String nomeCompleto;
    @JsonProperty("status")
    private String status;
    @JsonProperty("RAZÃO SOCIAL")
    private String razaoSocial;
    @JsonProperty("RUA/AV")
    private String ruaEndereco;
}