package Visual;

import javax.swing.*;
import java.awt.*;
import ClassesBase.*;

public class ConsultarEstoque extends JPanel {
	
	 private Estoque estoque;
	 private JTextField tfCodigo;
	 private JTextArea taResultado;

    public ConsultarEstoque(Estoque estoque) {
        this.estoque = estoque;
        inicializar();
    }

    private void inicializar() {
    	setLayout(new BorderLayout(10, 10));

        // Título
        JLabel titulo = new JLabel("Consultar Produto no Estoque", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Painel de busca
        JPanel busca = new JPanel();
        busca.add(new JLabel("Código do produto:"));

        tfCodigo = new JTextField(15);
        busca.add(tfCodigo);

        JButton btnBuscar = new JButton("Consultar");
        btnBuscar.addActionListener(e -> consultarProduto());
        busca.add(btnBuscar);

        add(busca, BorderLayout.CENTER);

        // Área de resultado
        taResultado = new JTextArea(8, 40);
        taResultado.setEditable(false);
        taResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));

        add(new JScrollPane(taResultado), BorderLayout.SOUTH);
    }

    private void consultarProduto() {

        String codigo = tfCodigo.getText().trim();

        if (codigo.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "Informe o código do produto.", "Aviso", JOptionPane.WARNING_MESSAGE);
        	return;
        }

        Produto encontrado = null;

        for (Produto p : estoque.getProdutos()) {
        	if (p.getCodigo().equalsIgnoreCase(codigo)) {
        		encontrado = p;
        		break;
	        }
	    }

        if (encontrado == null) {
        	taResultado.setText("Produto não encontrado.");
        } else {
        	taResultado.setText(encontrado.toString());
        }
	}
}
