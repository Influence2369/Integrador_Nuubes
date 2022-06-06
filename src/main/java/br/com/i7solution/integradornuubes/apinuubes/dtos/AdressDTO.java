package br.com.i7solution.integradornuubes.apinuubes.dtos;

import java.io.Serializable;

public class AdressDTO implements Serializable {

    private String costumerStreet;
    private Integer costumerNumber;
    private String costumerComplement;
    private String costumerDistrict;
    private String costumerCity;
    private String costumerCep;
    private String costumerUf;

    public String getCostumerStreet() {
        return costumerStreet;
    }

    public void setCostumerStreet(String costumerStreet) {
        this.costumerStreet = costumerStreet;
    }

    public Integer getCostumerNumber() {
        return costumerNumber;
    }

    public void setCostumerNumber(Integer costumerNumber) {
        this.costumerNumber = costumerNumber;
    }

    public String getCostumerComplement() {
        return costumerComplement;
    }

    public void setCostumerComplement(String costumerComplement) {
        this.costumerComplement = costumerComplement;
    }

    public String getCostumerDistrict() {
        return costumerDistrict;
    }

    public void setCostumerDistrict(String costumerDistrict) {
        this.costumerDistrict = costumerDistrict;
    }

    public String getCostumerCity() {
        return costumerCity;
    }

    public void setCostumerCity(String costumerCity) {
        this.costumerCity = costumerCity;
    }

    public String getCostumerCep() {
        return costumerCep;
    }

    public void setCostumerCep(String costumerCep) {
        this.costumerCep = costumerCep;
    }

    public String getCostumerUf() {
        return costumerUf;
    }

    public void setCostumerUf(String costumerUf) {
        this.costumerUf = costumerUf;
    }
}
