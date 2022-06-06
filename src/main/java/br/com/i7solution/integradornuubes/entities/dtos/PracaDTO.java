package br.com.i7solution.integradornuubes.entities.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PracaDTO implements Serializable {

	private String id;
    private String descricao;

    public String toJson() {
        return "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"descricao\": \"" + descricao + "\"\n" +
               "}";
    }
}
