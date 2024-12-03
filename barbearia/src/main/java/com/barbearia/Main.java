package com.barbearia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.barbearia.repository.Agendamento.AgendamentoJdbcRepository;
import com.barbearia.repository.Agendamento.AgendamentoRepository;
import com.barbearia.repository.Cliente.ClienteJdbcRepository;
import com.barbearia.repository.Cliente.ClienteRepository;
import com.barbearia.repository.Servico.ServicoJdbcRepository;
import com.barbearia.repository.Servico.ServicoRepository;
import com.barbearia.service.Agendamento.AgendamentoService;
import com.barbearia.service.Cliente.ClienteService;
import com.barbearia.service.Servico.ServicoService;

public class Main {
    public static void main(String[] args) {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Bloco try para garantir que o Scanner será fechado
            try (Scanner scanner = new Scanner(System.in)) {
                // Estabelece a conexão com o banco de dados
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbearia", "root", "root");

                // Cria instâncias do repositório e do serviço
                ServicoJdbcRepository jdbcRepository = new ServicoJdbcRepository(conn);
                ServicoRepository repository = new ServicoJdbcRepository(conn);
                ServicoService service = new ServicoService(repository, jdbcRepository);

                ClienteJdbcRepository jdbcRepositoryCliente = new ClienteJdbcRepository(conn);
                ClienteRepository repositoryCliente = new ClienteJdbcRepository(conn);
                ClienteService serviceCliente = new ClienteService(repositoryCliente, jdbcRepositoryCliente);

                AgendamentoJdbcRepository jdbcRepositoryAgendamento = new AgendamentoJdbcRepository(conn);
                AgendamentoRepository repositoryAgendamento = new AgendamentoJdbcRepository(conn);
                AgendamentoService serviceAgendamento = new AgendamentoService(repositoryAgendamento,
                        jdbcRepositoryAgendamento);

                // Loop principal para escolher entre Serviços, Clientes e Pagamentos
                while (true) {
                    System.out.println(
                            "\nEscolha uma categoria: 1- Serviços 2- Clientes 3-Agendamentos  4-Pagamentos 5- Sair");
                    int categoria = 0;
                    try {
                        categoria = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                        continue;
                    }

                    switch (categoria) {
                        case 1:
                            // Loop específico para operações de serviços
                            while (true) {
                                System.out.println(
                                        "\nEscolha uma operação: 1- Cadastrar Serviço 2- Editar Serviço 3- Detalhes do Serviço 4- Voltar");
                                int opcao = 0;
                                try {
                                    opcao = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                                    continue;
                                }
                                switch (opcao) {
                                    case 1:
                                        System.out.print("Nome: ");
                                        String nome = scanner.nextLine();
                                        System.out.print("Descrição: ");
                                        String descricao = scanner.nextLine();
                                        System.out.print("Preço: ");
                                        Double preco = null;
                                        try {
                                            preco = Double.parseDouble(scanner.nextLine());
                                        } catch (NumberFormatException e) {
                                            System.out.println("Erro: Preço inválido.");
                                            break;
                                        }
                                        service.criarServico(nome, descricao, preco);
                                        break;
                                    case 2:
                                        boolean servicoValido = false;
                                        while (!servicoValido) {
                                            System.out.print("Nome do serviço a ser editado: ");
                                            nome = scanner.nextLine();
                                            System.out.print("Novo nome: ");
                                            String novoNome = scanner.nextLine();
                                            System.out.print("Nova descrição: ");
                                            descricao = scanner.nextLine();
                                            System.out.print("Novo preço: ");
                                            preco = null;
                                            try {
                                                preco = Double.parseDouble(scanner.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Erro: Preço inválido.");
                                                break;
                                            }
                                            servicoValido = service.editarServico(nome, novoNome, descricao, preco);
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Nome do serviço a ser visualizado: ");
                                        nome = scanner.nextLine();
                                        service.detalhesServico(nome);
                                        break;
                                    case 4:
                                        // Voltar ao menu principal
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                                if (opcao == 4) {
                                    break;
                                }
                            }
                            break;
                        case 2:
                            while (true) {
                                System.out.println(
                                        "\nEscolha uma operação: 1- Cadastrar Cliente 2- Atualizar o total de serviços 3- Verificar fidelidade 4- Voltar");
                                int opcao = 0;
                                try {
                                    opcao = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                                    continue;
                                }
                                switch (opcao) {
                                    case 1:
                                        System.out.print("Nome: ");
                                        String nome = scanner.nextLine();
                                        System.out.print("CPF: ");
                                        String cpf = scanner.nextLine();
                                        System.out.print("Serviços realizados: ");
                                        Integer servicosRealizados = null;
                                        try {
                                            servicosRealizados = Integer.parseInt(scanner.nextLine());
                                        } catch (NumberFormatException e) {
                                            System.out.println("Erro: Serviços realizados inválidos.");
                                            break;
                                        }
                                        serviceCliente.criarCliente(nome, cpf, servicosRealizados);
                                        break;
                                    case 2:
                                        System.out.print("CPF do cliente: ");
                                        cpf = scanner.nextLine();
                                        System.out.print("Novo total de serviços realizados: ");
                                        servicosRealizados = null;
                                        try {
                                            servicosRealizados = Integer.parseInt(scanner.nextLine());
                                        } catch (NumberFormatException e) {
                                            System.out.println("Erro: Serviços realizados inválidos.");
                                            break;
                                        }
                                        serviceCliente.registrarServico(cpf, servicosRealizados);
                                        break;
                                    case 3:
                                        System.out.print("CPF do cliente: ");
                                        cpf = scanner.nextLine();
                                        serviceCliente.verificarFidelidade(cpf);
                                        break;
                                    case 4:
                                        // Voltar ao menu principal
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                                if (opcao == 2) {
                                    break;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("\nEscolha uma operação: 1- Finalizar Agendamento 2- Voltar");
                            int opcao = 0;
                            try {
                                opcao = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                                continue;
                            }
                            switch (opcao) {
                                case 1:
                                    System.out.print("CPF do cliente: ");
                                    String cpf = scanner.nextLine();
                                    System.out.print("Serviço: ");
                                    String servico = scanner.nextLine();
                                    serviceAgendamento.finalizarAgendamento(cpf, servico);
                                    break;
                                case 2:
                                    // Voltar ao menu principal
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }
                            if (opcao == 2) {
                                break;
                            }
                            break;
                        case 4:
                            // Adicione aqui o loop específico para operações de pagamentos
                            System.out.println("Operações de Pagamentos ainda não implementadas.");
                            break;
                        case 5:
                            // Operação para sair do programa
                            System.out.println("Saindo...");
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                }
            }
        } catch (

        ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC do MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}