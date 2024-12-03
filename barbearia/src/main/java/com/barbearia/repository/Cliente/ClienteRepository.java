package com.barbearia.repository.Cliente;

import com.barbearia.entity.Cliente;

public interface ClienteRepository {
    void criar(Cliente cliente) throws Exception;

    void registrarServico(String cpf, Integer servicosRealizados) throws Exception;

    void verificarFidelidade(String cpf) throws Exception;
}
