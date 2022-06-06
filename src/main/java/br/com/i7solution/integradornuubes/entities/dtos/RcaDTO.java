package br.com.i7solution.integradornuubes.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class RcaDTO implements Serializable {
    private String id;
    private String nome;
    private Long ordem;

    public String toJson() {
        return "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"nome\": \"" + nome + "\",\n" +
                "  \"ordem\": \"" + ordem.toString() + "\"\n" +
               "}";
    }
}
