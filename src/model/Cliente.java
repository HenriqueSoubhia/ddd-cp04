package model;

public class Cliente {
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private double renda;

    public Cliente(String cpf, String nome, String email, double renda) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.renda = renda;
    }

    public Cliente(int id, String cpf, String nome, String email, double renda) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.renda = renda;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getRenda() { return renda; }
    public void setRenda(double renda) { this.renda = renda; }
}
