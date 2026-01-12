package Visual;

import javax.swing.*;
import java.awt.*;
import ClassesBase.*;

public class TelaPrincipal extends JFrame{
	
	private CardLayout cardLayout;
	private JPanel painelCentral;
	private Estoque estoque = new Estoque();
	private GerenciarOS gerenciarOS	= new GerenciarOS();
	private HistoricoVendas telaHistorico;
	
	public TelaPrincipal() {
		
		estoque = new Estoque();
		setTitle("AKA System");
		setSize(900, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		criarMenu();
		criarPainelCentral();
		
		setVisible(true);
	}
	
	private void criarMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Menu");
		
		JMenuItem itemCadastro = new JMenuItem("Cadastro de Produtos");
		JMenuItem itemEstoque = new JMenuItem("Exibir Estoque");
		JMenuItem itemConsulta = new JMenuItem("Consultar Estoque");
		JMenuItem itemVenda = new JMenuItem("Registrar Venda");
		JMenuItem itemOS = new JMenuItem("Ordem de Serviço");
		JMenuItem itemHV = new JMenuItem("Histórico de vendas");
		
		
		itemCadastro.addActionListener(e -> cardLayout.show(painelCentral, "CADASTRO"));
		
		itemEstoque.addActionListener(e -> cardLayout.show(painelCentral, "ESTOQUE"));
		
		itemConsulta.addActionListener(e -> cardLayout.show(painelCentral, "CONSULTA"));
		
		itemVenda.addActionListener(e -> cardLayout.show(painelCentral, "REGISTRAR VENDA"));
		
		itemOS.addActionListener(e -> cardLayout.show(painelCentral, "OS"));
		
		itemHV.addActionListener(e -> {
			telaHistorico.atualizarTabela(); 
			cardLayout.show(painelCentral, "HV");
		});
		
		
		menu.add(itemCadastro);
		menu.add(itemEstoque);
		menu.add(itemConsulta);
		menu.add(itemVenda);
		menu.add(itemOS);
		menu.add(itemHV);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
	}
	
	private void criarPainelCentral() {
		
		cardLayout = new CardLayout();
		painelCentral = new JPanel(cardLayout);
		
		painelCentral.add(new CadastroProdutos(estoque), "CADASTRO");
		painelCentral.add(new ExibirEstoque(estoque), "ESTOQUE");
		painelCentral.add(new ConsultarEstoque(estoque), "CONSULTA");
		painelCentral.add(new RegistrarVenda(estoque), "REGISTRAR VENDA");
		painelCentral.add(new GerenciarOrdemServico(gerenciarOS), "OS");
		
		telaHistorico = new HistoricoVendas(estoque);
		painelCentral.add(telaHistorico, "HV");
		
		
		add(painelCentral);
		
		//tela inicial
		cardLayout.show(painelCentral, "CADASTRO");
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(TelaPrincipal::new);
	}
}

