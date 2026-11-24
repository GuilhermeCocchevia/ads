package ingressos.servico;

import ingressos.excecoes.IngressoInvalidoException;
import ingressos.interfaces.Validavel;
import ingressos.modelo.Evento;
import ingressos.modelo.Ingresso;
import ingressos.modelo.StatusIngresso;

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

    /** Venda (botão Cadastrar). */
    public Ingresso vender(String nomeComprador, boolean meia, String documento)
            throws IngressoInvalidoException {
        // lotacao: contagem apenas de ingressos nao cancelados
        int ocupados = 0;
        for (Ingresso i : ingressos) {
            if (i.getStatus() != StatusIngresso.CANCELADO) {
                ocupados++;
            }
        }

        // apenas > deixaria 100 > 100 passar como false e seria feita a venda do centésimo primeiro ingresso (erro)
        if (ocupados >= evento.getCapacidade()) {
            throw new IngressoInvalidoException("O evento atingiu lotação máxima.");
        }

        //venda de meia necessita documento
        if (meia && (documento == null || documento.isBlank())) {
            throw new IngressoInvalidoException("A venda de meia entrada é válida apenas após comprovação via documento com foto");
        }

        Ingresso novo;
        if(meia) { // a tela informa se o usuário selecionou meia ou inteira
            novo = new IngressoMeia(proximoCodigo++, nomeComprador, evento, documento);
        } else {
            novo = new IngressoInteira(proximoCodigo++, nomeComprador, evento);
        }

        ingressos.add(novo);

        return novo;
    }

    /** Busca pelo código (botão Pesquisar). */
    public Ingresso pesquisar(int codigo) throws IngressoInvalidoException {
        // método static da interface: chamado pelo nome dela, sem precisar de objeto [POO_10]
        // vale também para a catraca e o cancelamento, que chamam o pesquisar
        if (!Validavel.codigoValido(codigo)) {
            throw new IngressoInvalidoException("Código inválido: deve ser maior que zero.");
        }

        for (Ingresso i : ingressos) {
            if (i.getCodigo() == codigo) {
                return i;
            }
        }
        throw new IngressoInvalidoException("Ingresso " + codigo + " não encontrado.");
    }

    /** Conferência na portaria com baixa (botão Processar Operação). */
    public Ingresso validarNaCatraca(int codigo) throws IngressoInvalidoException {
        // se o código não existir, o pesquisar já lança a exceção
        Ingresso ingresso = pesquisar(codigo);

        // inteira ou meia: cada uma valida do seu jeito (polimorfismo)
        ingresso.validarAcessoCatraca();
        return ingresso;
    }

    /** Cancelamento. */
    public void cancelar(int codigo) throws IngressoInvalidoException {
        Ingresso ingresso = pesquisar(codigo);

        // só dá para cancelar ingresso que ainda não foi usado
        if (ingresso.getStatus() == StatusIngresso.UTILIZADO) {
            throw new IngressoInvalidoException("Ingresso " + codigo + " já foi utilizado e não pode ser cancelado.");
        } else if (ingresso.getStatus() == StatusIngresso.CANCELADO) {
            throw new IngressoInvalidoException("Ingresso " + codigo + " já está cancelado.");
        }

        ingresso.setStatus(StatusIngresso.CANCELADO);
    }

    /** Ordenação natural (botão Ordenar). */
    public void ordenar() {
       Collections.sort(ingressos);
    }

    /** Relatório de público para a JTextArea. */
    public String relatorioPublico() {
        String texto = "RELATÓRIO DE PÚBLICO - " + evento + "\n\n";

        int vendidos = 0;
        int utilizados = 0;
        int cancelados = 0;
        double arrecadacao = 0;

        // uma linha para cada ingresso e vai somando os totais
        for (Ingresso i : ingressos) {
            texto += i + "\n";

            if (i.getStatus() == StatusIngresso.VENDIDO) {
                vendidos++;
            } else if (i.getStatus() == StatusIngresso.UTILIZADO) {
                utilizados++;
            } else {
                cancelados++;
            }

            // ingresso cancelado não conta na arrecadação (o dinheiro foi devolvido)
            if (i.getStatus() != StatusIngresso.CANCELADO) {
                arrecadacao += i.calcularPreco();
            }
        }

        texto += "\nVendidos (ainda não entraram): " + vendidos;
        texto += "\nUtilizados (já entraram): " + utilizados;
        texto += "\nCancelados: " + cancelados;
        texto += "\nTotal de ingressos: " + ingressos.size();
        texto += "\nArrecadação: R$ " + String.format("%.2f", arrecadacao);
        return texto;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }
}