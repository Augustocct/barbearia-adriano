package com.barbearia.repository.Servico;

import com.barbearia.entity.Servico;

public interface ServicoRepository {
    // Método para criar um novo componente
    void criar(Servico servico) throws Exception;

    void editar(Servico servico, String nomeOriginal) throws Exception;

    void detalhesServico(String nome) throws Exception;
}
