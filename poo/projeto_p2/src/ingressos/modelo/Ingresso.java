package ingressos.modelo;

import ingressos.excecoes.IngressoInvalidoException;
import ingressos.interfaces.Validavel;

/**
 * Entidade principal. Abstrata: cada tipo (inteira, meia) define o próprio preço
 * e a própria regra de validação na catraca (polimorfismo).
 * Ordem natural (Comparable): código sequencial do bilhete. [POO_09]
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
        // ingresso que já entrou não pode entrar de novo
        if (status == StatusIngresso.UTILIZADO) {
            throw new IngressoInvalidoException("Ingresso " + codigo + " já foi utilizado.");
        } else if (status == StatusIngresso.CANCELADO) {
            throw new IngressoInvalidoException("Ingresso " + codigo + " está cancelado.");
        }

        // passou: dá baixa no ingresso
        status = StatusIngresso.UTILIZADO;
    }

    @Override
    public int compareTo(Ingresso outro) {
        // TODO: comparar pelo código (-1, 0, 1), como no Cliente do Sample4
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: dois ingressos são iguais se tiverem o mesmo código (modelo da Pessoa do Sample1)
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        // TODO: retornar algo baseado no código
        return super.hashCode();
    }

    @Override
    public String toString() {
        // TODO: montar uma linha legível para o relatório (código, tipo, comprador, preço, status)
        return "Ingresso " + codigo;
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
