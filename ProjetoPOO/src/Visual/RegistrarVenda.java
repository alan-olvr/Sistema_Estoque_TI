package Visual;

import javax.swing.*;
import java.awt.*;
import ClassesBase.*;

public class RegistrarVenda extends JPanel {
	
	private Estoque estoque;
    private JTextField tfCodigo, tfQuantidade;

    public RegistrarVenda(Estoque estoque) {
        this.estoque = estoque;
        inicializar();
    }

    private void inicializar() {

        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Registrar Venda", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));

        tfCodigo = new JTextField();
        tfQuantidade = new JTextField();

        form.add(new JLabel("Código do produto:"));
        form.add(tfCodigo);
        form.add(new JLabel("Quantidade vendida:"));
        form.add(tfQuantidade);

        JButton btnVender = new JButton("Registrar Venda");
        btnVender.addActionListener(e -> registrarVenda());

        add(form, BorderLayout.CENTER);
        add(btnVender, BorderLayout.SOUTH);
    }

    private void registrarVenda() {

    	try {
            String codigo = tfCodigo.getText().trim();
            int qtd = Integer.parseInt(tfQuantidade.getText());

            if (codigo.isEmpty() || qtd <= 0) {
                throw new IllegalArgumentException("Dados inválidos.");
            }

            estoque.registrarVenda(codigo, qtd);

            JOptionPane.showMessageDialog(this,
                    "Venda registrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Quantidade inválida.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IllegalStateException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        tfCodigo.setText("");
        tfQuantidade.setText("");
    }
}
