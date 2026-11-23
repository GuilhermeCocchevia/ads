package ingressos.modelo;

public class IngressoInteira extends Ingresso {

    public IngressoInteira(int codigo, String nomeComprador, Evento evento) {
        super(codigo, nomeComprador, evento);
    }

    @Override
    public double calcularPreco() {
        Evento e = getEvento();
        double pb = e.getPrecoBase();
        return pb;
        //escrito em uma linha/encadeado: return getEvento().getPrecoBase();
    }

    @Override
    public String getTipo() {
        return "Inteira";
    }
}
