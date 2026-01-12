package Visual;

import javax.swing.*;
import java.awt.*;
import ClassesBase.*;

public class CadastroProdutos extends JPanel {
	private Estoque estoque;
	
	private JTextField tfCodigo, tfNome, tfPreco, tfQtd;
	private JComboBox<String> cbTipo;
	
	private JLabel lbC1, lbC2, lbC3;
	private JTextField tfC1, tfC2, tfC3;
	
	public CadastroProdutos(Estoque estoque) {
		this.estoque = estoque;
		inicializar();
	}
	
	private void inicializar() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Cadastro de Produtos", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        tfCodigo = new JTextField();
        tfNome = new JTextField();
        tfPreco = new JTextField();
        tfQtd = new JTextField();

        cbTipo = new JComboBox<>(new String[]{
                "Hardware", "Perifericos", "Software", "Dispositivo"
        });

        form.add(new JLabel("Código:"));
        form.add(tfCodigo);
        form.add(new JLabel("Nome:"));
        form.add(tfNome);
        form.add(new JLabel("Preço:"));
        form.add(tfPreco);
        form.add(new JLabel("Quantidade:"));
        form.add(tfQtd);
        form.add(new JLabel("Tipo:"));
        form.add(cbTipo);
        
        painelCaracteristicas(form);
        add(form, BorderLayout.CENTER);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarProduto());
        add(btnCadastrar, BorderLayout.SOUTH);
        
        cbTipo.addActionListener(e -> atualizarRotulos(cbTipo.getSelectedItem().toString()));
        
        //estado inicial
        atualizarRotulos("Hardware");
    }
	
	private void painelCaracteristicas(JPanel form) {
		lbC1 = new JLabel();
		lbC2 = new JLabel();
		lbC3 = new JLabel();
		
		tfC1 = new JTextField();
		tfC2 = new JTextField();
		tfC3 = new JTextField();	
		
		form.add(lbC1);
		form.add(tfC1);
		form.add(lbC2);
		form.add(tfC2);
		form.add(lbC3);
		form.add(tfC3);
	}
	
	private void atualizarRotulos(String tipo) {
		//limpa campos
		tfC1.setText("");
		tfC2.setText("");
		tfC3.setText("");
		
		switch(tipo) {
		case "Hardware": 
			lbC1.setText("Fabricante: ");
			lbC2.setText("Garantia (meses): ");
			lbC3.setText("(opcional)");
		break;
		
		case "Perifericos":
			lbC1.setText("Tipo: ");
			lbC2.setText("Garantia (meses): ");
			lbC3.setText("(opcional)");
		break;
		
		case "Software":
			lbC1.setText("Licença: ");
			lbC2.setText("Sistema Operacional: ");
			lbC3.setText("(opcional)");
		break;
		
		case "Dispositivo":
			lbC1.setText("Tipo: ");
			lbC2.setText("Marca: ");
			lbC3.setText("Garantia (meses): ");
		}
	}
	
	private void cadastrarProduto() {
		try {
			
			String codigo = tfCodigo.getText().trim();
			String nome = tfNome.getText().trim();
			double preco = Double.parseDouble(tfPreco.getText());
			int qtd = Integer.parseInt(tfQtd.getText());
			
			Produto p;
			String tipo = cbTipo.getSelectedItem().toString();
			
			switch(tipo) {
			case "Hardware": 
				p = new Hardware(codigo, nome, preco, qtd, tfC1.getText(), Integer.parseInt(tfC2.getText()));
			break;
			
			case "Perifericos":
				p = new Perifericos(codigo, nome, preco, qtd, tfC1.getText(), Integer.parseInt(tfC2.getText()));
			break;
			
			case "Software": 
				p = new Software(codigo, nome, preco, qtd, tfC1.getText(), tfC2.getText());
			break;
			
			case "Dispositivo":
				p = new Dispositivo(codigo, nome, preco, qtd, Integer.parseInt(tfC3.getText()), tfC1.getText(), tfC2.getText());
			break;
			
			default:
				throw new IllegalArgumentException("Tipo inválido.");
			
			}
			
			estoque.adicionarProduto(p);
			JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
			
			limparCampos();
			
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Erro: valores numéricos inválidos.", "Erro" , JOptionPane.ERROR_MESSAGE);
		} catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limparCampos() {
		tfCodigo.setText("");
		tfNome.setText("");
		tfPreco.setText("");
		tfQtd.setText("");
		tfC1.setText("");
		tfC2.setText("");
		tfC3.setText("");
	}
}
