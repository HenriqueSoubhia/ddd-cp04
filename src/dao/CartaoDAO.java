package dao;

import model.Cartao;
import java.sql.*;
import java.util.Random;

public class CartaoDAO {

    static Connection conn = ConnectionFactory.getConnection();

    public void salvar(Cartao cartao) throws SQLException {
        // Pegando próximo ID da sequência
        String seqSql = "SELECT seq_cartao.NEXTVAL FROM dual";
        int novoId;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(seqSql);
            rs.next();
            novoId = rs.getInt(1);
        }

        String sql = "INSERT INTO cartao (id, numero, bandeira, limite_total, limite_disponivel, cliente_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoId);                   // ID da sequência
            stmt.setString(2, cartao.getNumero());   // Número do cartão
            stmt.setString(3, cartao.getBandeira()); // Bandeira
            stmt.setDouble(4, cartao.getLimiteTotal());
            stmt.setDouble(5, cartao.getLimiteDisponivel());
            stmt.setInt(6, cartao.getClienteId());
            stmt.executeUpdate();
            cartao.setId(novoId); // Atualiza o objeto com o ID gerado
        }
    }

    public static Cartao buscarPorNumero(String numero) throws SQLException {
        String sql = "SELECT * FROM cartao WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cartao(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getString("bandeira"),
                        rs.getDouble("limite_total"),
                        rs.getDouble("limite_disponivel"),
                        rs.getInt("cliente_id")
                );
            } else {
                return null;
            }
        }
    }

    public static String gerarNumeroCartao() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public static String gerarNumeroCartaoUnico() throws SQLException {
        String numero;
        do {
            numero = CartaoDAO.gerarNumeroCartao();
        } while (buscarPorNumero(numero) != null);
        return numero;
    }

    public void atualizar(Cartao cartao) throws SQLException {
        String sql = "UPDATE cartao SET bandeira = ?, limite_total = ?, limite_disponivel = ?, cliente_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cartao.getBandeira());
            stmt.setDouble(2, cartao.getLimiteTotal());
            stmt.setDouble(3, cartao.getLimiteDisponivel());
            stmt.setInt(4, cartao.getClienteId());
            stmt.setInt(5, cartao.getId());
            stmt.executeUpdate();
        }
    }

}
