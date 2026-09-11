package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import ClassesBase.*;

public class HistoricoVendas extends JPanel {
	private Estoque estoque;
    private JTable tabela;
    private DefaultTableModel model;
    private JLabel lbTotalVendas, lbValorTotal;

    public HistoricoVendas(Estoque estoque) {
        this.estoque = estoque;
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Histórico de Vendas", JLabel.CENTER);
        titulo.putClientProperty("FlatLaf.style", "font: bold +9");
        add(titulo, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{"Data", "Produto", "Quantidade", "Valor Total"}, 0
        );

        tabela = new JTable(model);
        tabela.setRowHeight(24);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel rodape = new JPanel(new GridLayout(1, 2, 10, 0));
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        lbTotalVendas = new JLabel("Total de vendas: 0");
        lbValorTotal = new JLabel("Valor total vendido: R$ 0,00");

        rodape.add(lbTotalVendas);
        rodape.add(lbValorTotal);

        add(rodape, BorderLayout.SOUTH);

        atualizarTabela();
    }

    public void atualizarTabela() {

        model.setRowCount(0);

        int totalVendas = 0;
        double valorTotal = 0.0;

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (RegistroVenda rv : estoque.getHistoricoVendas()) {

            model.addRow(new Object[]{
                    rv.getDataHora().format(formato),
                    rv.getProduto().getNome(),
                    rv.getQuantidadeVendida(),
                    String.format("R$ %.2f", rv.getValorTotal())
            });

            totalVendas++;
            valorTotal += rv.getValorTotal();
        }

        lbTotalVendas.setText("Total de vendas: " + totalVendas);
        lbValorTotal.setText(
                String.format("Valor total vendido: R$ %.2f", valorTotal)
        );
        
    }
}
