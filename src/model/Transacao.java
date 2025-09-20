package model;

import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private int cartaoId;
    private LocalDateTime data;
    private String descricao;
    private double valor;
    private String status;

    public Transacao(int id, int cartaoId, LocalDateTime data, String descricao, double valor, String status) {
        this.id = id;
        this.cartaoId = cartaoId;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCartaoId() { return cartaoId; }
    public void setCartaoId(int cartaoId) { this.cartaoId = cartaoId; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
