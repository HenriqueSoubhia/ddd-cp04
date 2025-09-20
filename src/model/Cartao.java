package model;

public class Cartao {

    private int id;
    private String numero;
    private String bandeira;
    private double limiteTotal;
    private double limiteDisponivel;
    private int clienteId;

    public Cartao(String numero, String bandeira, double limiteTotal, double limiteDisponivel, int clienteId) {
        this.numero = numero;
        this.bandeira = bandeira;
        this.limiteTotal = limiteTotal;
        this.limiteDisponivel = limiteDisponivel;
        this.clienteId = clienteId;
    }

    public Cartao(int id, String numero, String bandeira, double limiteTotal, double limiteDisponivel, int clienteId) {
        this.id = id;
        this.numero = numero;
        this.bandeira = bandeira;
        this.limiteTotal = limiteTotal;
        this.limiteDisponivel = limiteDisponivel;
        this.clienteId = clienteId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getBandeira() {
        return bandeira;
    }
    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
    public double getLimiteTotal() {
        return limiteTotal;
    }
    public void setLimiteTotal(double limiteTotal) {
        this.limiteTotal = limiteTotal;
    }
    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }
    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }
    public int getClienteId() {
        return clienteId;
    }
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", bandeira='" + bandeira + '\'' +
                ", limiteTotal=" + limiteTotal +
                ", limiteDisponivel=" + limiteDisponivel +
                ", clienteId=" + clienteId +
                '}';
    }
}
