package com.barbearia.service.Cliente;

import com.barbearia.entity.Cliente;
import com.barbearia.repository.Cliente.ClienteJdbcRepository;
import com.barbearia.repository.Cliente.ClienteRepository;

public class ClienteService {
    private ClienteRepository repository;
    private ClienteJdbcRepository jdbcRepository;

    // Construtor que inicializa o repositório
    public ClienteService(ClienteRepository repository, ClienteJdbcRepository jdbcRepository) {
        this.repository = repository;
        this.jdbcRepository = jdbcRepository;
    }

    public void criarCliente(String nome, String cpf, Integer servicosRealizados) {
        try {
            if (jdbcRepository.existeCliente(cpf)) {
                System.out.println("Erro: Cliente com o mesmo CPF já existe.");
                return;
            }
            Cliente cliente = new Cliente(nome, cpf, servicosRealizados);
            repository.criar(cliente);
            System.out.println("Cliente criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar serviço: " + e.getMessage());
        }
    }

    public void registrarServico(String cpf, Integer servicosRealizados) {
        try {
            if (!jdbcRepository.existeCliente(cpf)) {
                System.out.println("Erro: Cliente não encontrado.");
                return;
            }
            repository.registrarServico(cpf, servicosRealizados);
            System.out.println("Sucesso ao atualizar o numero de serviços!");
        } catch (Exception e) {
            System.out.println("Erro ao registrar serviço: " + e.getMessage());
        }
    }

    public void verificarFidelidade(String cpf) {
        try {
            if (!jdbcRepository.existeCliente(cpf)) {
                System.out.println("Erro: Cliente não encontrado.");
                return;
            }
            repository.verificarFidelidade(cpf);
        } catch (Exception e) {
            System.out.println("Erro ao verificar fidelidade: " + e.getMessage());
        }
    }

}
