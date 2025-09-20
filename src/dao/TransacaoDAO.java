package dao;

import model.Transacao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    private Connection conn = ConnectionFactory.getConnection();

    public void salvar(Transacao t) throws SQLException {
        int novoId;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT seq_transacao.NEXTVAL FROM dual");
            rs.next();
            novoId = rs.getInt(1);
        }

        String sql = "INSERT INTO transacao (id, cartao_id, data, descricao, valor, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoId);
            stmt.setInt(2, t.getCartaoId());
            stmt.setTimestamp(3, Timestamp.valueOf(t.getData()));
            stmt.setString(4, t.getDescricao());
            stmt.setDouble(5, t.getValor());
            stmt.setString(6, t.getStatus());
            stmt.executeUpdate();
            t.setId(novoId);
        }
    }

    public List<Transacao> listarPorCartao(int cartaoId) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE cartao_id = ?";
        List<Transacao> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartaoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Transacao(
                        rs.getInt("id"),
                        rs.getInt("cartao_id"),
                        rs.getTimestamp("data").toLocalDateTime(),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("status")
                ));
            }
        }
        return lista;
    }

    public List<Transacao> listarAbertasPorCartaoMesAtual(int cartaoId) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE cartao_id = ? AND status = 'ABERTA' " +
                "AND EXTRACT(MONTH FROM data) = EXTRACT(MONTH FROM SYSDATE) " +
                "AND EXTRACT(YEAR FROM data) = EXTRACT(YEAR FROM SYSDATE)";
        List<Transacao> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartaoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Transacao(
                        rs.getInt("id"),
                        rs.getInt("cartao_id"),
                        rs.getTimestamp("data").toLocalDateTime(),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("status")
                ));
            }
        }
        return lista;
    }

    public void pagarFatura(int cartaoId) throws SQLException {
        String sql = "UPDATE transacao SET status = 'PAGA' WHERE cartao_id = ? AND status = 'ABERTA'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartaoId);
            stmt.executeUpdate();
        }
    }
}
