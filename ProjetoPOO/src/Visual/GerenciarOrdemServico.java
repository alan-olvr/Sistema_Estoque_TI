package Visual;

import javax.swing.*;
import java.awt.*;
import ClassesBase.*;
import javax.swing.table.DefaultTableModel;

public class GerenciarOrdemServico extends JPanel {
	 private GerenciarOS gerenciador;

	    private JTextField tfCliente, tfValor;
	    private JTextArea taProblema;

	    private JTable tabela;
	    private DefaultTableModel model;

	    public GerenciarOrdemServico(GerenciarOS gerenciador) {
	        this.gerenciador = gerenciador;
	        inicializar();
	    }

	    private void inicializar() {
	        setLayout(new BorderLayout(10, 10));
	        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	        JLabel titulo = new JLabel("Gerenciar Ordem de Serviço", JLabel.CENTER);
	        titulo.putClientProperty("FlatLaf.style", "font: bold +9");
	        add(titulo, BorderLayout.NORTH);

	        JPanel cadastro = new JPanel(new GridLayout(0, 2, 10, 10));

	        tfCliente = new JTextField();
	        taProblema = new JTextArea(3, 20);
	        tfValor = new JTextField();

	        cadastro.add(new JLabel("Cliente:"));
	        cadastro.add(tfCliente);
	        cadastro.add(new JLabel("Descrição do problema:"));
	        cadastro.add(new JScrollPane(taProblema));

	        JButton btnCriar = new JButton("Criar OS");
	        btnCriar.putClientProperty("JButton.buttonType", "roundRect");
	        btnCriar.addActionListener(e -> criarOS());

	        JPanel painelCadastro = new JPanel(new BorderLayout(0, 10));
	        painelCadastro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
	        painelCadastro.add(cadastro, BorderLayout.CENTER);
	        painelCadastro.add(btnCriar, BorderLayout.SOUTH);

	        add(painelCadastro, BorderLayout.WEST);

	        model = new DefaultTableModel(
	                new Object[]{"ID", "Cliente", "Status", "Valor"}, 0
	        );

	        tabela = new JTable(model);
	        tabela.setRowHeight(24);
	        add(new JScrollPane(tabela), BorderLayout.CENTER);

	        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

	        JButton btnAvancar = new JButton("Avançar Status");
	        JButton btnCancelar = new JButton("Cancelar OS");
	        JButton btnValor = new JButton("Definir Valor");

	        for (JButton b : new JButton[]{btnAvancar, btnCancelar, btnValor}) {
	            b.putClientProperty("JButton.buttonType", "roundRect");
	        }

	        btnAvancar.addActionListener(e -> avancarStatus());
	        btnCancelar.addActionListener(e -> cancelarOS());
	        btnValor.addActionListener(e -> definirValor());

	        acoes.add(btnAvancar);
	        acoes.add(btnCancelar);
	        acoes.add(btnValor);

	        add(acoes, BorderLayout.SOUTH);
	    }

	    private void criarOS() {

	        String cliente = tfCliente.getText().trim();
	        String problema = taProblema.getText().trim();

	        if (cliente.isEmpty() || problema.isEmpty()) {
	            JOptionPane.showMessageDialog(this,
	                    "Preencha todos os campos.",
	                    "Erro",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        gerenciador.criarOS(cliente, problema);
	        atualizarTabela();

	        tfCliente.setText("");
	        taProblema.setText("");
	    }

	    private void avancarStatus() {

	        OrdemServico os = obterSelecionada();
	        if (os == null) return;

	        try {
	            os.avancarStatus();
	            atualizarTabela();
	        } catch (IllegalStateException e) {
	            JOptionPane.showMessageDialog(this,
	                    e.getMessage(),
	                    "Aviso",
	                    JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void cancelarOS() {

	        OrdemServico os = obterSelecionada();
	        if (os == null) return;

	        os.cancelarOS();
	        atualizarTabela();
	    }

	    private void definirValor() {

	        OrdemServico os = obterSelecionada();
	        if (os == null) return;

	        try {
	            String valorStr = JOptionPane.showInputDialog(this, "Valor do serviço:");
	            if (valorStr == null) return;

	            double valor = Double.parseDouble(valorStr);
	            os.definirValor(valor);

	            atualizarTabela();

	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this,
	                    "Valor inválido.",
	                    "Erro",
	                    JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private OrdemServico obterSelecionada() {

	        int linha = tabela.getSelectedRow();

	        if (linha == -1) {
	            JOptionPane.showMessageDialog(this,
	                    "Selecione uma OS na tabela.",
	                    "Aviso",
	                    JOptionPane.WARNING_MESSAGE);
	            return null;
	        }

	        int id = (int) model.getValueAt(linha, 0);
	        return gerenciador.buscarOS(id);
	    }

	    private void atualizarTabela() {

	        model.setRowCount(0);

	        for (OrdemServico os : gerenciador.getListaOS()) {
	            model.addRow(new Object[]{
	                    os.getId(),
	                    os.getNomeCliente(), // cliente
	                    os.getStatus(),
	                    String.format("R$ %.2f", os.getValorServico())
	            });
	        }
	    }
}
