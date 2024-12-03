package com.barbearia.service.Agendamento;

import com.barbearia.repository.Agendamento.AgendamentoJdbcRepository;
import com.barbearia.repository.Agendamento.AgendamentoRepository;

public class AgendamentoService {

    private AgendamentoRepository repository;

    private AgendamentoJdbcRepository jdbcRepository;

    public AgendamentoService(AgendamentoRepository repository, AgendamentoJdbcRepository jdbcRepository) {
        this.repository = repository;
        this.jdbcRepository = jdbcRepository;
    }

}
