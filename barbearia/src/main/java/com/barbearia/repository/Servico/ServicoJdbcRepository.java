package com.barbearia.repository.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.barbearia.entity.Servico;

public class ServicoJdbcRepository implements ServicoRepository {

    private Connection conn;

    // Construtor que inicializa a conexão com o banco de dados
    public ServicoJdbcRepository(Connection conn) {
        this.conn = conn;
    }

    // Método para criar um novo componente no banco de dados
    @Override
    public void criar(Servico servico) throws SQLException {
        String sql = "INSERT INTO servico (nome, descricao, preco) VALUES (?, ?, ?)";
        // Usando PreparedStatement para evitar SQL Injection
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Configura os parâmetros da consulta
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getPreco());
            // Executa a consulta
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um componente no banco de dados
    @Override
    public void editar(Servico servico, String nomeOriginal) throws SQLException {
        // SQL para atualizar um componente existente
        String sql = "UPDATE servico SET nome = ?, descricao = ?, preco = ? WHERE nome = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Configura os parâmetros da consulta
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getPreco());
            stmt.setString(4, nomeOriginal);
            // Executa a consulta
            stmt.executeUpdate();
        }
    }

    public boolean existeServico(String nome, String descricao) throws SQLException {
        String sql = "SELECT COUNT(*) FROM servico WHERE nome = ? OR descricao = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public Servico buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM servico WHERE nome = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer id = rs.getInt("id");
                    String descricao = rs.getString("descricao");
                    double preco = rs.getDouble("preco");
                    return new Servico(id, nome, descricao, preco);
                }
            }
        }
        return null;
    }

    @Override
    public void detalhesServico(String nome) throws Exception {
        try {
            buscarPorNome(nome);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço no banco: " + e.getMessage());
        }
    }
}
