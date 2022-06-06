package br.com.i7solution.integradornuubes.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class FilialDTO implements Serializable {
	
	private String id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    public String toJson() {
        return "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"cnpj\": \"" + cnpj + "\",\n" +
                "  \"razaoSocial\": \"" + razaoSocial + "\",\n" +
                "  \"nomeFantasia\": \"" + nomeFantasia + "\"\n" +
               "}";
    }
}
