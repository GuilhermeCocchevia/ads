package ingressos.modelo;

import ingressos.excecoes.IngressoInvalidoException;
import ingressos.interfaces.Validavel;

/**
 * Entidade principal. Abstrata: cada tipo (inteira, meia) define o próprio preço e a própria regra de validação na catraca (polimorfismo).
 */
public abstract class Ingresso implements Comparable<Ingresso>, Validavel {
    private int codigo;
    private String nomeComprador;
    private Evento evento;
    private StatusIngresso status;

    public Ingresso(int codigo, String nomeComprador, Evento evento) {
        this.codigo = codigo;
        this.nomeComprador = nomeComprador;
        this.evento = evento;
        this.status = StatusIngresso.VENDIDO;
    }

    /** Cada subclasse calcula o seu preço. */
    public abstract double calcularPreco();

    /** "Inteira" ou "Meia" — usado no relatório. */
    public abstract String getTipo();

    @Override
    public void validarAcessoCatraca() throws IngressoInvalidoException {
        // TODO: se status != VENDIDO, lançar IngressoInvalidoException com a mensagem adequada
        //       se estiver ok, mudar status para UTILIZADO
    }

    @Override
    public int compareTo(Ingresso outro) {
        if (this.codigo < outro.codigo) {
            return -1; // esse ingresso vem ANTES do outro
        }
        if (this.codigo > outro.codigo) {
            return 1; // esse ingresso vem DEPOIS do outro
        }
        return 0; // mesmo codigo, "empate"
    }

    //this == o > comparação de endereço de memória
    //this.codigo == i.codigo > comparação de valor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Ingresso i) {
            return this.codigo == i.codigo;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.codigo;
    }

    //aparece no relatório e em pesquisar
    @Override
    public String toString() {
        return "Ingresso #" + codigo + " | " + getTipo() + " | " + nomeComprador + " | " + calcularPreco() + " | " + status;
    }

    // getters e setters feitos diretos pela ide; sem mistério
    public int getCodigo() {
        return codigo;
    }

    //sem setcodigo > encapsulamento: equals, hashCode e compareTo se basearão nele

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public StatusIngresso getStatus() {
        return status;
    }

    public void setStatus(StatusIngresso status) {
        this.status = status;
    }
}
