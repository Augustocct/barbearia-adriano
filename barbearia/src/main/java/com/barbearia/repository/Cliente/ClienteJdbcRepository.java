package com.barbearia.repository.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.barbearia.entity.Cliente;

public class ClienteJdbcRepository implements ClienteRepository {

    private Connection conn;

    public ClienteJdbcRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void criar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, cpf, servicos_realizados) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getServicosRealizados());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao registrar cliente: " + e.getMessage());
        }
    }

    public boolean existeCliente(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cliente WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public void registrarServico(String cpf, Integer servicosRealizados) throws SQLException {
        String sql = "UPDATE cliente SET servicos_realizados = ? WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicosRealizados);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao registrar serviço: " + e.getMessage());
        }
    }

    @Override
    public void verificarFidelidade(String cpf) throws SQLException {
        String sql = "SELECT servicos_realizados FROM cliente WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int servicosRealizados = rs.getInt("servicos_realizados");
                    if (servicosRealizados >= 10) {
                        System.out.println("O Cliente tem direito ao desconto!");
                    } else {
                        System.out.println("O Cliente não tem direito ao desconto.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar fidelidade: " + e.getMessage());
        }
    }

}
