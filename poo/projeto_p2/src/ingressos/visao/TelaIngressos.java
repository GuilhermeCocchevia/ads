package ingressos.visao;

import ingressos.modelo.Evento;
import ingressos.servico.Bilheteria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/** Tela principal, no estilo TelaMovimento / TelaCalculadora. [POO_07] */
public class TelaIngressos extends JFrame implements ActionListener, WindowListener {

    private final Bilheteria bilheteria = new Bilheteria(new Evento("Show de Exemplo", 100, 80.0));

    // Campos
    private final JTextField txtComprador = new JTextField();
    private final JTextField txtCodigo = new JTextField();
    // TODO: campo do documento da meia e escolha inteira/meia (ex.: JCheckBox ou dois JRadioButton)

    // Botões obrigatórios
    private final JButton btnCadastrar = new JButton("Cadastrar");
    private final JButton btnPesquisar = new JButton("Pesquisar");
    private final JButton btnProcessar = new JButton("Processar Operação");
    private final JButton btnOrdenar = new JButton("Ordenar");
    private final JButton btnSair = new JButton("Sair");
    // TODO: botões Cancelar e Relatório

    private final JTextArea txtRelatorio = new JTextArea();

    public TelaIngressos() {
        setTitle("Controle de Eventos e Venda de Ingressos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // quem fecha é o windowClosing
        setLayout(null); // posicionamento manual com setBounds, como nos exemplos de aula

        // TODO: posicionar JLabels, campos, botões e o JScrollPane com setBounds e adicionar com add(...)

        for (JButton b : new JButton[]{btnCadastrar, btnPesquisar, btnProcessar, btnOrdenar, btnSair}) {
            b.addActionListener(this);
        }
        addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origem = e.getSource();
        if (origem == btnSair) {
            fechar();
        }
        // TODO: um else-if para cada botão, com try/catch de
        //       IngressoInvalidoException e NumberFormatException → JOptionPane.showMessageDialog
    }

    private void fechar() {
        int resp = JOptionPane.showConfirmDialog(this, "Deseja sair do sistema?", "Sair",
                JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Os 7 métodos do WindowListener precisam existir
    @Override public void windowClosing(WindowEvent e) { fechar(); }
    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}
}
