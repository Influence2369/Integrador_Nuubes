package br.com.i7solution.integradornuubes.apinuubes.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerListDTO implements Serializable {
    private CustomerDTO[] data;
    private boolean success;
    private String message;
}
