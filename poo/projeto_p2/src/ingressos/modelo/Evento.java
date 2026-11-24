package ingressos.modelo;

public class Evento {
    private String nome;
    private int capacidade;     // lotação máxima
    private double precoBase;   // preço da inteira

    public Evento(String nome, int capacidade, double precoBase) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.precoBase = precoBase;
    }

    @Override
    public String toString() {
        return nome + " (capacidade: " + capacidade + ")";
    }

    // getters e setters feitos diretos pela ide; sem mistério
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }
}
