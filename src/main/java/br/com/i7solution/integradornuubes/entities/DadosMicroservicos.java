package br.com.i7solution.integradornuubes.entities;

public class DadosMicroservicos {
    static private String baseUrl = "http://srv62463.oracledba.com.br:5006/api";
    static public String endPointVinculos = baseUrl + "/centaurus/vinculos/produtos";
    static public String endPointCliente = baseUrl + "/comet/clientes";
    static public String endPointPracas = baseUrl + "/comet/pracas";
    static public String endPointCep = baseUrl + "/comet/enderecos/consultar/cep";

    static public String idProduto = "61af8214-63ce-4b0d-933f-63e8b5ce2d32";
}
