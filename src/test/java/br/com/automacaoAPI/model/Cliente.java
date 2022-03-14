package br.com.automacaoAPI.model;

import br.com.automacaoAPI.teste.BaseTeste;

import java.math.BigDecimal;

public class Cliente {

    private String nome;
    private String cpf;
    private String email;
    private BigDecimal valor;
    private int parcelas;
    private boolean seguro;



    public Cliente( String nome, String cpf, String email, BigDecimal valor, int parcelas, boolean seguro) {

        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.valor = valor;
        this.parcelas = parcelas;
        this.seguro = seguro;
    }

    public Cliente(String nome, String email, BigDecimal valor, int parcelas, boolean seguro) {
        this.nome = nome;
        this.email = email;
        this.valor = valor;
        this.parcelas = parcelas;
        this.seguro = seguro;
    }



    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public boolean isSeguro() {
        return seguro;
    }
}
