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
        setLayout(new BorderLayout(10, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registrar Venda", JLabel.CENTER);
        titulo.putClientProperty("FlatLaf.style", "font: bold +9");
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        tfCodigo = new JTextField();
        tfQuantidade = new JTextField();

        form.add(new JLabel("Código do produto:"));
        form.add(tfCodigo);
        form.add(new JLabel("Quantidade vendida:"));
        form.add(tfQuantidade);

        JButton btnVender = new JButton("Registrar Venda");
        btnVender.putClientProperty("JButton.buttonType", "roundRect");
        btnVender.addActionListener(e -> registrarVenda());

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painelBotao.add(btnVender);

        add(form, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);
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
