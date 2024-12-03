package com.barbearia.repository.Agendamento;

public interface AgendamentoRepository {
    void finalizarAgendamento(String cpf, String servico) throws Exception;
}
