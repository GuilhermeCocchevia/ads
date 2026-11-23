package ingressos.interfaces;

import ingressos.excecoes.IngressoInvalidoException;

/** Contrato de negócio do tema 08. [POO_10] */
public interface Validavel {

    /** Abstrato: cada tipo de ingresso diz COMO é validado na portaria. */
    void validarAcessoCatraca() throws IngressoInvalidoException;

    /** Default: comportamento pronto, herdado por quem implementa. */
    default String gerarComprovanteAcesso() {
        // TODO: devolver um texto tipo "ACESSO LIBERADO - ..." (pode usar toString())
        return "ACESSO LIBERADO";
    }

    /** Static: utilitário da própria interface. */
    static boolean codigoValido(int codigo) {
        return codigo > 0;
    }
}
