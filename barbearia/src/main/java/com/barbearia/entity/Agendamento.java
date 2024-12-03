package com.barbearia.entity;

import java.time.LocalDateTime;

public class Agendamento {
    private Cliente cliente;
    private Servico servico;
    private LocalDateTime Horario;

    public Agendamento(Cliente cliente, Servico servico, LocalDateTime horario) {
        this.cliente = cliente;
        this.servico = servico;
        Horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDateTime getHorario() {
        return Horario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setHorario(LocalDateTime horario) {
        Horario = horario;
    }
}
