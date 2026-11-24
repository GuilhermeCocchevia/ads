package ingressos.servico;

import ingressos.excecoes.IngressoInvalidoException;
import ingressos.modelo.Evento;
import ingressos.modelo.Ingresso;

import java.util.ArrayList;
import java.util.List;


import java.util.Collections;
import ingressos.modelo.IngressoInteira;
import ingressos.modelo.IngressoMeia;


/** Guarda os ingressos em memória e concentra as regras de negócio. */
public class Bilheteria {
    private final List<Ingresso> ingressos = new ArrayList<>();
    private final Evento evento;
    private int proximoCodigo = 1;

    public Bilheteria(Evento evento) {
        this.evento = evento;
    }

    public Ingresso vender(String nomeComprador, boolean meia, String documento)
            throws IngressoInvalidoException {
        return null;
    }

    /** Busca pelo código (botão Pesquisar). */
    public Ingresso pesquisar(int codigo) throws IngressoInvalidoException {
        for (Ingresso i : ingressos) {
            if (i.getCodigo() == codigo) {
                return i;
            }
        }
        throw new IngressoInvalidoException("Ingresso " + codigo + " não encontrado.");
    }

    /** Conferência na portaria com baixa (botão Processar Operação). */
    public Ingresso validarNaCatraca(int codigo) throws IngressoInvalidoException {
        // TODO: pesquisar(codigo) e chamar validarAcessoCatraca() — polimorfismo
        return null;
    }

    /** Cancelamento. */
    public void cancelar(int codigo) throws IngressoInvalidoException {
        // TODO: só cancela se ainda estiver VENDIDO
    }

    /** Ordenação natural (botão Ordenar). */
    public void ordenar() {
       Collections.sort(ingressos);
    }

    /** Relatório de público para a JTextArea. */
    public String relatorioPublico() {
        // TODO: listar os ingressos + totais (vendidos, utilizados, cancelados, arrecadação)
        return "";
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }
}
