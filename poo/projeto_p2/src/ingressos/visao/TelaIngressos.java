package ingressos.visao;

import ingressos.excecoes.IngressoInvalidoException;
import ingressos.modelo.Evento;
import ingressos.modelo.Ingresso;
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
    private final JTextField txtDocumento = new JTextField();               // documento da meia
    private final JCheckBox chkMeia = new JCheckBox("Meia-entrada");        // marcado = meia
    private final JLabel lblComprador = new JLabel("Comprador:");
    private final JLabel lblCodigo = new JLabel("Código:");
    private final JLabel lblDocumento = new JLabel("Documento:");

    // Botões obrigatórios
    private final JButton btnCadastrar = new JButton("Cadastrar");
    private final JButton btnPesquisar = new JButton("Pesquisar");
    private final JButton btnProcessar = new JButton("Processar Operação");
    private final JButton btnOrdenar = new JButton("Ordenar");
    private final JButton btnSair = new JButton("Sair");
    // TODO (Guilherme): botões Cancelar e Relatório

    private final JTextArea txtRelatorio = new JTextArea();

    public TelaIngressos() {
        setTitle("Controle de Eventos e Venda de Ingressos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // quem fecha é o windowClosing
        setLayout(null); // posicionamento manual com setBounds, como nos exemplos de aula

        // setBounds(x, y, largura, altura): (0, 0) é o canto superior esquerdo e o y cresce para baixo
        // rótulos em x=20, campos em x=110; cada linha desce 35 px

        // linha 1: comprador
        lblComprador.setBounds(20, 20, 90, 25);
        add(lblComprador);
        txtComprador.setBounds(110, 20, 200, 25);
        add(txtComprador);

        // linha 2: documento + opção de meia
        lblDocumento.setBounds(20, 55, 90, 25);
        add(lblDocumento);
        txtDocumento.setBounds(110, 55, 200, 25);
        add(txtDocumento);
        chkMeia.setBounds(320, 55, 130, 25);
        add(chkMeia);

        // linha 3: código (usado por Pesquisar, Processar Operação e Cancelar)
        lblCodigo.setBounds(20, 90, 90, 25);
        add(lblCodigo);
        txtCodigo.setBounds(110, 90, 100, 25);
        add(txtCodigo);

        // botões obrigatórios
        btnCadastrar.setBounds(20, 135, 110, 30);
        add(btnCadastrar);
        btnPesquisar.setBounds(140, 135, 110, 30);
        add(btnPesquisar);
        btnProcessar.setBounds(260, 135, 160, 30);
        add(btnProcessar);
        btnOrdenar.setBounds(430, 135, 100, 30);
        add(btnOrdenar);
        btnSair.setBounds(540, 135, 100, 30);
        add(btnSair);

        // TODO (Guilherme): linha y=175 reservada para Cancelar e Relatório

        // área de texto dentro de um JScrollPane (barra de rolagem)
        // quem vai para a janela é o JScrollPane, não a JTextArea
        txtRelatorio.setEditable(false); // o usuário só lê
        JScrollPane rolagem = new JScrollPane(txtRelatorio);
        rolagem.setBounds(20, 220, 645, 220);
        add(rolagem);

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

        // VENDA: a Bilheteria valida (lotação, documento da meia) e lança a exceção se algo estiver errado
        if (origem == btnCadastrar) {
            try {
                Ingresso novo = bilheteria.vender(txtComprador.getText(),
                        chkMeia.isSelected(), txtDocumento.getText());
                txtRelatorio.append("Vendido: " + novo + "\n");
                limparCampos();
            } catch (IngressoInvalidoException ex) {
                mostrarErro(ex.getMessage());
            }
        }

        // PESQUISA: dois erros possíveis, texto que não é número ou código que não existe
        if (origem == btnPesquisar) {
            try {
                Ingresso achado = bilheteria.pesquisar(lerCodigo());
                txtRelatorio.append("Encontrado: " + achado + "\n");
            } catch (NumberFormatException ex) {
                mostrarErro("Digite um código numérico válido.");
            } catch (IngressoInvalidoException ex) {
                mostrarErro(ex.getMessage());
            }
        }

        // ORDENAÇÃO: Collections.sort usa o compareTo do Ingresso (ordem pelo código)
        if (origem == btnOrdenar) {
            bilheteria.ordenar();
            txtRelatorio.setText("Ingressos ordenados por código:\n");
            for (Ingresso i : bilheteria.getIngressos()) {
                txtRelatorio.append(i + "\n");
            }
        }

        // TODO (Guilherme): Processar Operação (catraca), Cancelar e Relatório
    }

    /** Converte o campo Código em número; lança NumberFormatException se não for número. */
    private int lerCodigo() {
        return Integer.parseInt(txtCodigo.getText().trim());
    }

    /** Mostra o erro numa caixa de diálogo, como no TelaMovimento. [POO_08] */
    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aconteceu um erro", JOptionPane.ERROR_MESSAGE);
    }

    /** Limpa os campos de venda depois de um cadastro com sucesso. */
    private void limparCampos() {
        txtComprador.setText("");
        txtDocumento.setText("");
        chkMeia.setSelected(false);
    }

    private void fechar() {
        int resp = JOptionPane.showConfirmDialog(this, "Deseja sair do sistema?", "Sair",
                JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }