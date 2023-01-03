package br.com.i7solution.integradornuubes.entities.dtos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.i7solution.integradornuubes.tools.Ferramentas;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO implements Serializable {
	
	private String id;
    private String nome;
    private String nomeFantasia;
    private String cpfCnpj;
    private String cpfCnpjEntrega;
    private Date dataNascimento;
    private String tipoPessoa;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String email;
    private String telefoneCelular;
    private String telefoneFixo;
    private PlanoPagtoDTO planoPagamento;
    private boolean acessarPlanoPagamentosNegociados;
    private RcaDTO[] vendedores;
    private PracaDTO praca;
    private PracaDTO pracaCobranca;
    private EnderecoDTO[] enderecos;
    private ContatoDTO[] contatos;
    private boolean isentoIcms;
    private boolean isentoIpi;
    private boolean calculaSt;
    private boolean contribuinte;
    private boolean consumidorFinal;
    private String emailNfe;
    private String emailCobranca;
    private String emailRecebedor;
    private String telefoneCobranca;
    private String telefoneEntrega;
    private String telefoneComercial;
    private FilialDTO filial;
    private String name;
    private String error;
    private CobrancaDTO cobranca;
    private boolean ativo;
    

    public String toJson() {
        String listaVS = "";
        for (var v = 0; v < vendedores.length; v++) {
            listaVS += vendedores[v].toJson();
            if (v < vendedores.length - 1) {
                listaVS += ",\n";
            } else {
                listaVS += "\n";
            }
        }

        String listaES = "";
        for (var e = 0; e < enderecos.length; e++) {
            listaES += enderecos[e].toJson();
            if (e < enderecos.length - 1) {
                listaES += ",\n";
            } else {
//                listaVS += "\n";
                listaES += "\n";
            }
        }

        String listaCS = "";
        for (var c = 0; c < contatos.length; c++) {
            listaCS += contatos[c].toJson();
            if (c < contatos.length - 1) {
                listaCS += ",\n";
            } else {
//                listaVS += "\n";
                listaCS += "\n";
            }
        }
        
        if(this.praca == null) {
        	log.warn("Praca está nula ");
        }
        if(this.pracaCobranca == null) {
        	log.warn("Praca cobrança está nula ");
        }
        if(this.filial == null) {
        	log.warn("Filial está nula ");
        }
        
        log.warn("Caiu em ClienteDTO.toJson()");
        
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        sb.append("  \"id\": \"" + this.id + "\",\n");
        sb.append("  \"nome\": \"" + this.nome         + "\",\n");
        sb.append("  \"nomeFantasia\": \"" + this.nomeFantasia + "\",\n");
        sb.append("  \"cpfCnpj\": \"" + this.cpfCnpj + "\",\n");
        sb.append("  \"cpfCnpjEntrega\": \"" + this.cpfCnpjEntrega + "\",\n");
        sb.append("  \"dtNasc\": \"" + Ferramentas.dataFormatada(this.dataNascimento,"yyyy-MM-dd") + "\",\n");
        sb.append("  \"tipoPessoa\": \"" + this.tipoPessoa + "\",\n");
        sb.append("  \"inscricaoEstadual\": \"" + this.inscricaoEstadual + "\",\n");
        sb.append("  \"inscricaoMunicipal\": \"" + this.inscricaoMunicipal + "\",\n");
        sb.append("  \"ativo\": \"" + this.ativo + "\",\n");
        sb.append("  \"email\": \"" + this.email + "\",\n");
        sb.append("  \"telefoneCelular\": \"" + this.telefoneCelular + "\",\n");
        sb.append("  \"telefoneFixo\": \"" + this.telefoneFixo + "\",\n");
        sb.append("  \"planoPagamento\": " + this.planoPagamento.toJson() + ",\n");
        sb.append("  \"acessarPlanoPagamentosNegociados\": \"" + this.acessarPlanoPagamentosNegociados + "\",\n");
        sb.append("  \"vendedores\": [\n" + listaVS + "\n],\n");
        
//        sb.append("  \"praca\": " + this.praca == null ? "null" : this.praca.toJson() + ",\n");
        sb.append("  \"praca\": ");
        sb.append(this.praca == null ? "null" : this.praca.toJson());
        sb.append(",\n");
        
        
        
//        sb.append("  \"pracaCobranca\": " + this.pracaCobranca == null ? "null" : this.pracaCobranca.toJson() + ",\n");
        sb.append("  \"pracaCobranca\": ");
        sb.append(this.pracaCobranca == null ? "null" : this.pracaCobranca.toJson());
        sb.append(",\n");

        
        
        
        sb.append("  \"enderecos\": [\n" + listaES + "\n],\n");
        sb.append("  \"contatos\": [\n" + listaCS + "\n],\n");
        sb.append("  \"isentoIcms\": \"" + this.isentoIcms + "\",\n");
        sb.append("  \"isentoIpi\": \"" + this.isentoIpi + "\",\n");
        sb.append("  \"calculaSt\": \"" + this.calculaSt + "\",\n");
        sb.append("  \"contribuinte\": \"" + this.contribuinte + "\",\n");
        sb.append("  \"consumidorFinal\": \"" + this.consumidorFinal + "\",\n");
        sb.append("  \"emailNfe\": \"" + this.emailNfe + "\",\n");
        sb.append("  \"emailCobranca\": \"" + this.emailCobranca + "\",\n");
        sb.append("  \"emailRecebedor\": \"" + this.emailRecebedor + "\",\n");
        sb.append("  \"telefoneCobranca\": \"" + this.telefoneCobranca + "\",\n");
        sb.append("  \"telefoneEntrega\": \"" + this.telefoneEntrega + "\",\n");
        sb.append("  \"telefoneComercial\": \"" + this.telefoneComercial + "\",\n");

        
//        sb.append("  \"filial\":" + this.filial == null ? "null" : this.filial.toJson() + "\n");
        sb.append("  \"filial\":");
        sb.append(this.filial == null ? "null" : this.filial.toJson());
        sb.append("\n");
        
        
        sb.append("}");
        
        log.warn(sb.toString());
      
        
        return sb.toString();


        /*return "{" +
                "  \"id\": \"" + this.id + "\",\n" +
                "  \"nome\"        : \"" + this.nome         + "\",\n" +
                "  \"nomeFantasia\": \"" + this.nomeFantasia + "\",\n" +
                "  \"cpfCnpj\": \"" + this.cpfCnpj + "\",\n" +
                "  \"cpfCnpjEntrega\": \"" + this.cpfCnpjEntrega + "\",\n" +
                "  \"dtNasc\": \"" + Ferramentas.dataFormatada(this.dataNascimento,"yyyy-MM-dd") + "\",\n" +
                "  \"tipoPessoa\": \"" + this.tipoPessoa + "\",\n" +
                "  \"inscricaoEstadual\": \"" + this.inscricaoEstadual + "\",\n" +
                "  \"inscricaoMunicipal\": \"" + this.inscricaoMunicipal + "\",\n" +
                "  \"email\": \"" + this.email + "\",\n" +
                "  \"telefoneCelular\": \"" + this.telefoneCelular + "\",\n" +
                "  \"telefoneFixo\": \"" + this.telefoneFixo + "\",\n" +
                "  \"planoPagamento\": " + this.planoPagamento.toJson() + ",\n" +
                "  \"acessarPlanoPagamentosNegociados\": \"" + this.acessarPlanoPagamentosNegociados + "\",\n" +
                "  \"vendedores\": [\n" + listaVS + "\n],\n" +
                "  \"praca\": " + this.praca == null ? "" : this.praca.toJson() + ",\n" +
                "  \"pracaCobranca\": " + this.pracaCobranca == null ? "" : this.pracaCobranca.toJson() + ",\n" +
                "  \"enderecos\": [\n" + listaES + "\n],\n" +
                "  \"contatos\": [\n" + listaCS + "\n],\n" +
                "  \"isentoIcms\": \"" + this.isentoIcms + "\",\n" +
                "  \"isentoIpi\": \"" + this.isentoIpi + "\",\n" +
                "  \"calculaSt\": \"" + this.calculaSt + "\",\n" +
                "  \"contribuinte\": \"" + this.contribuinte + "\",\n" +
                "  \"consumidorFinal\": \"" + this.consumidorFinal + "\",\n" +
                "  \"emailNfe\": \"" + this.emailNfe + "\",\n" +
                "  \"emailCobranca\": \"" + this.emailCobranca + "\",\n" +
                "  \"emailRecebedor\": \"" + this.emailRecebedor + "\",\n" +
                "  \"telefoneCobranca\": \"" + this.telefoneCobranca + "\",\n" +
                "  \"telefoneEntrega\": \"" + this.telefoneEntrega + "\",\n" +
                "  \"telefoneComercial\": \"" + this.telefoneComercial + "\",\n" +
                "  \"filial\":" + this.filial == null ? "" : this.filial.toJson() + "\n" +
               "}";*/
        
        
    }
}

