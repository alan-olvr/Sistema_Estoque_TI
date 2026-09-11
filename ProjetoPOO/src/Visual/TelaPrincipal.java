package Visual;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.Collections;
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
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		criarMenu();
		criarPainelCentral();
		
		setVisible(true);
	}
	
	private void criarMenu() {
		JPanel menuBar = new JPanel();
		
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
		menuBar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		menuBar.setPreferredSize(new Dimension(180, getHeight()));
		
		JButton btnCadastro = new JButton("Cadastro de Produtos");
		JButton btnEstoque = new JButton("Exibir Estoque");
		JButton btnConsulta = new JButton("Consultar Estoque");
		JButton btnVenda = new JButton("Registrar Venda");
		JButton btnOS = new JButton("Ordem de Serviço");
		JButton btnHV = new JButton("Histórico de vendas");
		
		
		btnCadastro.addActionListener(e -> cardLayout.show(painelCentral, "CADASTRO"));
		
		btnEstoque.addActionListener(e -> cardLayout.show(painelCentral, "ESTOQUE"));
		
		btnConsulta.addActionListener(e -> cardLayout.show(painelCentral, "CONSULTA"));
		
		btnVenda.addActionListener(e -> cardLayout.show(painelCentral, "REGISTRAR VENDA"));
		
		btnOS.addActionListener(e -> cardLayout.show(painelCentral, "OS"));
		
		btnHV.addActionListener(e -> {
			telaHistorico.atualizarTabela(); 
			cardLayout.show(painelCentral, "HV");
		});
		
		
		for (JButton botao: new JButton[]{btnCadastro, btnEstoque, btnConsulta, btnVenda, btnOS, btnHV}) {
		    botao.setAlignmentX(Component.CENTER_ALIGNMENT);
	        botao.setMaximumSize(new Dimension(160, 40));
	        menuBar.add(botao);
	        menuBar.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		add(menuBar, BorderLayout.WEST);
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
	    FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#2F6FED"));

	    try {
	        UIManager.setLookAndFeel(new FlatLightLaf());
	    } catch (Exception ex) {
	        System.err.println("Falha ao inicializar o FlatLaf");
	    }

	    SwingUtilities.invokeLater(TelaPrincipal::new);
	}
}

