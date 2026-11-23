package ingressos;

import ingressos.visao.TelaIngressos;

/** Ponto de entrada: instancia a tela inicial e a torna visível. */
public class Principal {
    public static void main(String[] args) {
        TelaIngressos tela = new TelaIngressos();
        tela.setVisible(true);
    }
}
