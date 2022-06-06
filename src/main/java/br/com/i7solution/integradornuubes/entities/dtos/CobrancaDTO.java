package br.com.i7solution.integradornuubes.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class CobrancaDTO implements Serializable {
    private String id;
    private String descricao;

    public String toJson() {
        return "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"descricao\": \"" + descricao + "\"\n" +
                "}";
    }
}