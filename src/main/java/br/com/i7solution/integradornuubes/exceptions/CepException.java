package br.com.i7solution.integradornuubes.exceptions;

public class CepException extends Exception {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
