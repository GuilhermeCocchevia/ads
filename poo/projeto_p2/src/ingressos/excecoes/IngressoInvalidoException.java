package ingressos.excecoes;

/** Checked exception do tema 08, nos moldes da SemSaldoException. [POO_08] */
public class IngressoInvalidoException extends Exception {
    public IngressoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
