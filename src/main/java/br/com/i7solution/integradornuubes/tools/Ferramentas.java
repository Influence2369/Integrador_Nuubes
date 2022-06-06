package br.com.i7solution.integradornuubes.tools;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Ferramentas {

    static public Date stringToDate(String valor, String formatoEntrada) {
        try{
            Date data = new SimpleDateFormat(formatoEntrada).parse(valor);
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    static public Long stringToLong(String valor) {
        try{
            return Long.parseLong(valor);
        } catch (Exception e) {
            return null;
        }
    }

    static public String dataFormatada(Date data, String formato) {
        try {
            SimpleDateFormat formatador = new SimpleDateFormat(formato);
            String dataFormatada = formatador.format(data);

            return dataFormatada;
        } catch (Exception e) {
            return null;
        }
    }

    static public String verificarTipoFJ(String documento) {
        if (documento.length() == 11 || documento.length() == 14) {
            var doc = formatarCpfCnpj(documento, "F");
            if (doc.length() == 14) {
                return "F";
            } else {
                return "J";
            }
        }
        return "J";
    }

    static public String formatarCpfCnpj(String valor, String tipo) {
        try {
            var texto = valor
                    .replace(".","")
                    .replace("/","")
                    .replace("-","");

            if (tipo.equalsIgnoreCase("F")) {
                String bloco1 = texto.substring(0, 3);
                String bloco2 = texto.substring(3, 6);
                String bloco3 = texto.substring(6, 9);
                String bloco4 = texto.substring(9, 11);

                var cpf = bloco1 + "." + bloco2 + "."+ bloco3 + "-" + bloco4;

                return cpf;
            } else {
                String bloco1 = texto.substring(0, 2);
                String bloco2 = texto.substring(2, 5);
                String bloco3 = texto.substring(5, 8);
                String bloco4 = texto.substring(8, 12);
                String bloco5 = texto.substring(12, 14);

                var cnpj = bloco1 + "." + bloco2 + "."+ bloco3 + "/" + bloco4 + "-" + bloco5;

                return cnpj;
            }
        } catch (Exception e) {
            return valor;
        }
    }

    static public String somenteNumeros(String str) {
        try {
            String digitos = "";
            char[] letras  = str.toCharArray();
            for (char letra : letras) {
                if(Character.isDigit(letra)) {
                    digitos += letra;
                }
            }
            return digitos;
        } catch (Exception e) {
            return str;
        }
    }

    static public String removerAcentos(String str) {
        try {
            String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(nfdNormalizedString).replaceAll("");
        } catch (Exception e) {
            return str;
        }
    }

    static public String abreviarEstado(String nomeEstado) {
        String estado = Ferramentas.removerAcentos(nomeEstado.trim());
        if (estado.equalsIgnoreCase("Rio Grande do Sul")) {
            return "RS";
        }
        if (estado.equalsIgnoreCase("Santa Catarina")) {
            return "SC";
        }
        if (estado.equalsIgnoreCase("Parana")) {
            return "PR";
        }
        if (estado.equalsIgnoreCase("Sao Paulo")) {
            return "SP";
        }
        if (estado.equalsIgnoreCase("Minas Gerais")) {
            return "MG";
        }
        if (estado.equalsIgnoreCase("Espirito Santo")) {
            return "ES";
        }
        if (estado.equalsIgnoreCase("Rio de Janeiro")) {
            return "RJ";
        }
        if (estado.equalsIgnoreCase("Pernambuco")) {
            return "PE";
        }
        if (estado.equalsIgnoreCase("Bahia")) {
            return "BA";
        }
        if (estado.equalsIgnoreCase("Piaui")) {
            return "PI";
        }
        if (estado.equalsIgnoreCase("Alagoas")) {
            return "AL";
        }
        if (estado.equalsIgnoreCase("Sergipe")) {
            return "SE";
        }
        if (estado.equalsIgnoreCase("Paraiba")) {
            return "PB";
        }
        if (estado.equalsIgnoreCase("Maranhao")) {
            return "MA";
        }
        if (estado.equalsIgnoreCase("Rio Grande do Norte")) {
            return "RN";
        }
        if (estado.equalsIgnoreCase("Ceara")) {
            return "CE";
        }
        if (estado.equalsIgnoreCase("Roraima")) {
            return "RR";
        }
        if (estado.equalsIgnoreCase("Rondonia")) {
            return "RO";
        }
        if (estado.equalsIgnoreCase("Acre")) {
            return "AC";
        }
        if (estado.equalsIgnoreCase("Amapa")) {
            return "AM";
        }
        if (estado.equalsIgnoreCase("Para")) {
            return "PA";
        }
        if (estado.equalsIgnoreCase("Tocantins")) {
            return "TO";
        }
        if (estado.equalsIgnoreCase("Mato Grosso")) {
            return "MT";
        }
        if (estado.equalsIgnoreCase("Mato Grosso do Sul")) {
            return "MS";
        }
        if (estado.equalsIgnoreCase("Goias")) {
            return "GO";
        }
        if (estado.equalsIgnoreCase("Distrito Federal")) {
            return "DF";
        }

        return estado.toUpperCase();
    }
}
