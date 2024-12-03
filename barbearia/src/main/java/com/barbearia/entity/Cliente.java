package com.barbearia.entity;

public class Cliente {
    private String nome;
    private String cpf;
    private Integer servicosRealizados;

    public Cliente(String nome, String cpf, Integer servicosRealizados) {
        this.nome = nome;
        this.cpf = cpf;
        this.servicosRealizados = servicosRealizados;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getServicosRealizados() {
        return servicosRealizados;
    }
}
