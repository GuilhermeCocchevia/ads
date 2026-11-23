package ingressos.modelo;

import ingressos.excecoes.IngressoInvalidoException;

public class IngressoMeia extends Ingresso {
    private String documentoComprovante; // carteirinha de estudante, doador de sangue, servidor público,  etc.

    public IngressoMeia(int codigo, String nomeComprador, Evento evento, String documentoComprovante) {
        super(codigo, nomeComprador, evento);
        this.documentoComprovante = documentoComprovante;
    }

    @Override
    public double calcularPreco() {
        Evento e = getEvento();                 // "seleciona" o evento desse ingresso
        double pb = e.getPrecoBase();            // pega o preço base desse evento
        return pb / 2;                           // meia
    }

    @Override
    public String getTipo() {
        return "Meia";
    }

    @Override
    public void validarAcessoCatraca() throws IngressoInvalidoException {
        // TODO: regra extra da meia: sem documento comprovante → IngressoInvalidoException
        //       depois, chamar super.validarAcessoCatraca()
        super.validarAcessoCatraca();
    }

}
