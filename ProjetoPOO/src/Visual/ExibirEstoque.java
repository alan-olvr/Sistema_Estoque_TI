package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import ClassesBase.*;

public class ExibirEstoque extends JPanel {

	private Estoque estoque;
	
	private JComboBox<String> cbTipo;
	private JTable tabela;
	private DefaultTableModel model;
	private JLabel lbTotalQtd, lbTotalValor;
	
	public ExibirEstoque(Estoque estoque) {
		this.estoque = estoque;
		inicializar();
	}
	
	private void inicializar() {
		setLayout(new BorderLayout(10, 10));
	    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    JPanel norte = new JPanel(new BorderLayout(5, 10));

	    JLabel title = new JLabel("Estoque de Produtos", JLabel.CENTER);
	    title.putClientProperty("FlatLaf.style", "font: bold +9");

	    JPanel topo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
	    cbTipo = new JComboBox<>(new String[] {
	            "Hardware", "Perifericos", "Software", "Dispositivo"
	    });

	    JButton btnAtualizar = new JButton("Exibir");
	    btnAtualizar.putClientProperty("JButton.buttonType", "roundRect");
	    btnAtualizar.addActionListener(e -> atualizarTabela());

	    topo.add(new JLabel("Tipo:"));
	    topo.add(cbTipo);
	    topo.add(btnAtualizar);

	    norte.add(title, BorderLayout.NORTH);
	    norte.add(topo, BorderLayout.SOUTH);
	    add(norte, BorderLayout.NORTH);

	    model = new DefaultTableModel(
	            new Object[] {"Código", "Nome", "Quantidade", "Preço", "Valor em estoque"}, 0
	    );

	    tabela = new JTable(model);
	    tabela.setRowHeight(24);
	    add(new JScrollPane(tabela), BorderLayout.CENTER);

	    JPanel rodape = new JPanel(new GridLayout(1, 2, 10, 0));
	    rodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
	    lbTotalQtd = new JLabel("Quantidade Total: 0");
	    lbTotalValor = new JLabel("Valor total: R$ 0,00");

	    rodape.add(lbTotalQtd);
	    rodape.add(lbTotalValor);
	    add(rodape, BorderLayout.SOUTH);
	}
	
	private void atualizarTabela() {
		model.setRowCount(0);
		
		int totalQtd = 0;
		double totalValor = 0;
		
		String tipo = cbTipo.getSelectedItem().toString();
		
		for(Produto p: estoque.getProdutos()) {
			
			if(correspondeTipo(p, tipo)) {
				double valorEstoque = p.valorEstoque();
				
				model.addRow(new Object[] {
						p.getCodigo(), 
						p.getNome(),
						p.getQuantity(),
						p.getPrice(),
						valorEstoque
				});
				
				totalQtd += p.getQuantity();
				totalValor += valorEstoque;
			}
		}
		
		lbTotalQtd.setText("Quantidade total: " + totalQtd);
		lbTotalValor.setText(String.format("Valor em estoque: R$ %.2f", totalValor));
		
	}
	
	private boolean correspondeTipo(Produto p, String tipo) {
		switch(tipo) {
		case "Hardware":
			return p instanceof Hardware;
		case "Perifericos":
			return p instanceof Perifericos;
		case "Software":
			return p instanceof Software;
		case "Dispositivo":
			return p instanceof Dispositivo;
		default:
			return false;
		}
	}
}
