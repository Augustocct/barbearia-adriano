package com.barbearia.service.Servico;

import java.sql.SQLException;

import com.barbearia.entity.Servico;
import com.barbearia.repository.Servico.ServicoJdbcRepository;
import com.barbearia.repository.Servico.ServicoRepository;

public class ServicoService {

    private ServicoRepository repository;

    private ServicoJdbcRepository jdbcRepository;

    // Construtor que inicializa o repositório
    public ServicoService(ServicoRepository repository, ServicoJdbcRepository jdbcRepository) {
        this.repository = repository;
        this.jdbcRepository = jdbcRepository;
    }

    public void criarServico(String nome, String descricao, Double preco) {
        try {
            if (jdbcRepository.existeServico(nome, descricao)) {
                System.out.println("Erro: Serviço com o mesmo nome ou descrição já existe.");
                return;
            }
            Servico servico = new Servico(nome, descricao, preco);
            repository.criar(servico);
            System.out.println("Serviço criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar serviço: " + e.getMessage());
        }
    }

    // Método para atualizar um servico existente
    public boolean editarServico(String nome, String novoNome, String novaDescricao, Double novoPreco) {
        try {
            if (jdbcRepository.existeServico(novoNome, novaDescricao)) {
                System.out.println("Erro: Serviço com o mesmo nome ou descrição já existe.");
                return false;
            }

            // Verifica se o serviço existe
            Servico servicoExistente = jdbcRepository.buscarPorNome(nome);
            if (servicoExistente == null) {
                System.out.println("Erro: Serviço não encontrado. Tente novamente com um serviço que exista.");
                return false;
            }

            // Atualiza os dados do serviço existente
            Servico servicoAtualizado = new Servico(novoNome, novaDescricao, novoPreco);
            jdbcRepository.editar(servicoAtualizado, nome);
            System.out.println("Serviço atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
            return false;
        }
    }

    public void detalhesServico(String nome) {
        try {
            Servico servico = jdbcRepository.buscarPorNome(nome);
            if (servico == null) {
                System.out.println("Erro: Serviço não encontrado.");
                return;
            }
            System.out.println("Detalhes do Serviço:");
            System.out.println("Id: " + servico.getId());
            System.out.println("Nome: " + servico.getNome());
            System.out.println("Descrição: " + servico.getDescricao());
            System.out.println("Preço: " + servico.getPreco());
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço: " + e.getMessage());
        }
    }
}
