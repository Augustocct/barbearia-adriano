package com.barbearia.repository.Agendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendamentoJdbcRepository implements AgendamentoRepository {

    private Connection conn;

    public AgendamentoJdbcRepository(Connection conn) {
        this.conn = conn;
    }
}
