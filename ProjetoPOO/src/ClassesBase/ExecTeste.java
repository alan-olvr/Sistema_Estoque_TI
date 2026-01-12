package ClassesBase;

import java.util.Locale;
import java.util.Scanner;

public class ExecTeste {
	
	//método secundário para realizar o polimorfismo
	public static Produto instancyProduct(int opc, Scanner sc) {
		
		if(opc < 1 || opc > 4) {
			throw new IllegalArgumentException("Opção inexistente.");
		}
		
		System.out.println();
		System.out.println("- Insira os dados do produto abaixo -");
		System.out.print("Código: ");
		String codigo = sc.nextLine();
		
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		
		System.out.print("Preço: ");
		double price = sc.nextDouble();
		sc.nextLine();
		
		System.out.print("Quantidade: ");
		int qtd = sc.nextInt();
		sc.nextLine();
		
		switch(opc) {
		case 1: 
			System.out.print("Fabricante: ");
			String fabricante = sc.nextLine();
			
			System.out.print("Garantia (em meses): ");
			int garantia = sc.nextInt();
			sc.nextLine();
			
			return new Hardware(codigo, nome, price, qtd, fabricante, garantia);
			
		case 2: 
			System.out.print("Tipo: ");
			String tipo = sc.nextLine();
			
			System.out.print("Garantia (meses): ");
			int garant = sc.nextInt();
			sc.nextLine();
			
			return new Perifericos(codigo, nome, price, qtd, tipo, garant);
		
		case 3: 
			System.out.print("Licença: ");
			String licenca = sc.nextLine();
			
			System.out.print("Sistema Operacional: ");
			String SO = sc.nextLine();
			
			return new Software(codigo, nome, price, qtd, licenca, SO);
			
		case 4: 
	
			System.out.print("Tipo: ");
			String type = sc.nextLine();
			
			System.out.print("Marca: ");
			String marca = sc.nextLine();
			
			System.out.println("Garantia (meses): ");
			int grnt = sc.nextInt();
			sc.nextLine();
			
			return new Dispositivo(codigo, nome, price, qtd, grnt, type, marca);
		
		default:
			
		}
		return null;

	}
	
	//métodos auxiliares para o menu
	private static void menuCadastrarProdutos(Scanner sc, Estoque estoque) {

	    char continuar;

	    do {
	        try {
	            System.out.println("\n---------- CADASTRO DE PRODUTOS ----------");
	            System.out.println("1 - Hardware");
	            System.out.println("2 - Periféricos");
	            System.out.println("3 - Software");
	            System.out.println("4 - Dispositivos");
	            System.out.println("----------------------------------------");
	            System.out.print("Opção desejada: ");

	            int opc = sc.nextInt();
	            sc.nextLine();

	            Produto p = instancyProduct(opc, sc);
	            estoque.adicionarProduto(p);

	            System.out.println("\n✔ Produto cadastrado com sucesso!");
	            System.out.println("---------------------------------");
	            System.out.println(p);

	        } catch (IllegalArgumentException e) {
	            System.out.println("\n✖ Erro no cadastro: " + e.getMessage());
	        } catch (java.util.InputMismatchException e) {
	            System.out.println("\n✖ Entrada inválida. Digite apenas números.");
	            sc.nextLine(); // limpa buffer
	        }

	        System.out.println("-----------------------------------");
	        System.out.print("Deseja continuar cadastrando produtos? (S/N): ");
	        continuar = sc.next().toLowerCase().charAt(0);
	        sc.nextLine();

	    } while (continuar == 's');
	}

	
	private static void menuExibirEstoque(Scanner sc, Estoque estoque) {

	    char continuar;

	    do {
	        System.out.println("\n---------- EXIBIR ESTOQUE ----------");
	        System.out.println("1 - Hardwares");
	        System.out.println("2 - Periféricos");
	        System.out.println("3 - Softwares");
	        System.out.println("4 - Dispositivos");
	        System.out.println("-----------------------------------");
	        System.out.print("Selecione uma opção: ");

	        try {
	            int opcaoEst = sc.nextInt();
	            sc.nextLine();

	            estoque.exibirEstoque(opcaoEst);

	        } catch (IllegalArgumentException e) {
	            System.out.println("✖ Erro: " + e.getMessage());
	        } catch (java.util.InputMismatchException e) {
	            System.out.println("✖ Entrada inválida. Digite apenas números.");
	            sc.nextLine(); // limpa buffer
	        }

	        System.out.println("-----------------------------------");
	        System.out.print("Deseja continuar exibindo o estoque? (S/N): ");
	        continuar = sc.next().toLowerCase().charAt(0);
	        sc.nextLine();

	    } while (continuar == 's');
	}

	
	private static void menuConsultarProduto(Scanner sc, Estoque estoque) {

	    char continuar;

	    do {
	        System.out.println("\n---------- CONSULTAR PRODUTO ----------");
	        System.out.print("Digite o código do produto: ");
	        String codBusca = sc.nextLine();

	        try {
	            Produto encontrado = estoque.buscarProdutoPorCodigo(codBusca);

	            System.out.println("\nProduto encontrado:");
	            System.out.println(encontrado);

	        } catch (IllegalArgumentException | IllegalStateException e) {
	            System.out.println("\n✖ Erro: " + e.getMessage());
	        }

	        System.out.println("---------------------------------------");
	        System.out.print("Deseja continuar consultando produtos? (S/N): ");
	        continuar = sc.next().toLowerCase().charAt(0);
	        sc.nextLine();

	    } while (continuar == 's');
	}

	 
	 private static void menuRegistrarVenda(Scanner sc, Estoque estoque) {

		    char continuar;

		    do {
		        System.out.println("\n---------- REGISTRAR VENDA ----------");
		        System.out.print("Código do produto: ");
		        String codVenda = sc.nextLine();

		        try {
		            Produto prodVenda = estoque.buscarProdutoPorCodigo(codVenda);

		            System.out.println("\nProduto selecionado:");
		            System.out.println(prodVenda);

		            System.out.print("\nQuantidade desejada: ");
		            int qtdVenda = sc.nextInt();
		            sc.nextLine();

		            estoque.registrarVenda(codVenda, qtdVenda);

		            System.out.println("\n✔ Venda registrada com sucesso!");
		            System.out.printf(
		                "Produto: %s | Qtd: %d | Total: R$ %.2f\n",
		                prodVenda.getNome(),
		                qtdVenda,
		                prodVenda.getPrice() * qtdVenda
		            );

		        } catch (IllegalArgumentException | IllegalStateException e) {
		            System.out.println("\n✖ Erro: " + e.getMessage());

		        } catch (java.util.InputMismatchException e) {
		            System.out.println("\n✖ Entrada inválida. Digite apenas números.");
		            sc.nextLine(); // limpa buffer
		        }

		        System.out.println("---------------------------------------");
		        System.out.print("Deseja continuar registrando vendas? (S/N): ");
		        continuar = sc.next().toLowerCase().charAt(0);
		        sc.nextLine();

		    } while (continuar == 's');
		}

	 
	 private static void menuGerenciarOS(Scanner sc, GerenciarOS gerenciadorOS) {

		    System.out.println("\n------- GERENCIAR ORDEM DE SERVIÇO -------");
		    System.out.println("1 - Abrir Nova OS");
		    System.out.println("2 - Listar Todas as OS");
		    System.out.println("3 - Atualizar Status / Valor");
		    System.out.println("0 - Voltar");
		    System.out.print("Opção: ");

		    try {
		        int opcOS = sc.nextInt();
		        sc.nextLine();

		        switch (opcOS) {

		            case 1 -> {
		                char continuar;
		                do {
		                    System.out.println("\n---------- NOVA ORDEM DE SERVIÇO ----------");
		                    System.out.print("Nome do Cliente: ");
		                    String cliente = sc.nextLine();

		                    System.out.print("Descrição do Problema: ");
		                    String problema = sc.nextLine();

		                    gerenciadorOS.criarOS(cliente, problema);

		                    System.out.print("Deseja continuar cadastrando OS? (S/N): ");
		                    continuar = sc.next().toLowerCase().charAt(0);
		                    sc.nextLine();

		                } while (continuar == 's');
		            }

		            case 2 -> gerenciadorOS.listarTodasOS();

		            case 3 -> {
		                char continuarUpd = 0;
		                do {
		                    System.out.println("\n---------- ATUALIZAR OS ----------");
		                    System.out.print("Digite o ID da OS: ");
		                    int idOS = sc.nextInt();
		                    sc.nextLine();

		                    OrdemServico osEncontrada = gerenciadorOS.buscarOS(idOS);

		                    if (osEncontrada == null) {
		                        System.out.println("✖ OS não encontrada.");
		                        continue;
		                    }

		                    System.out.println("\nOS SELECIONADA:");
		                    System.out.println(osEncontrada);

		                    System.out.println("1 - Avançar Status");
		                    System.out.println("2 - Definir Valor");
		                    System.out.println("3 - Cancelar");
		                    System.out.print("Ação: ");

		                    int acao = sc.nextInt();
		                    sc.nextLine();

		                    try {
		                        switch (acao) {
		                            case 1 -> osEncontrada.avancarStatus();
		                            case 2 -> {
		                                System.out.print("Novo valor: ");
		                                double valor = sc.nextDouble();
		                                sc.nextLine();
		                                osEncontrada.definirValor(valor);
		                            }
		                            case 3 -> osEncontrada.cancelarOS();
		                            default -> System.out.println("Opção inválida.");
		                        }
		                    } catch (IllegalArgumentException | IllegalStateException e) {
		                        System.out.println("✖ Erro: " + e.getMessage());
		                    }

		                    System.out.print("Deseja continuar atualizando OS? (S/N): ");
		                    continuarUpd = sc.next().toLowerCase().charAt(0);
		                    sc.nextLine();

		                } while (continuarUpd == 's');
		            }

		            case 0 -> System.out.println("Voltando ao menu principal...");

		            default -> System.out.println("Opção inválida.");
		        }

		    } catch (java.util.InputMismatchException e) {
		        System.out.println("✖ Entrada inválida.");
		        sc.nextLine();
		    }
		}

	 private static void menuHistoricoVendas(Estoque estoque) {
		    System.out.println();
		    estoque.exibirHistoricoVendas();
		    System.out.println();
		}
	 
	
	//area de execucao do programa (no momento testando o cadastro)
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Estoque estoque = new Estoque();
		
		GerenciarOS gerenciadorOS = new GerenciarOS();
		boolean executando = true;
		
		while(executando) {
			
			//menu com as 6 funcionalidades do sistema
			System.out.println("=======================================");
			System.out.println("         MENU PRINCIPAL");
			System.out.println("=======================================");
			System.out.println("1 - Cadastrar produtos");//concluído.
			System.out.println("2 - Exibir estoque");//concluído.
			System.out.println("3 - Consultar estoque");//andré 
			System.out.println("4 - Registrar venda");//andré
			System.out.println("5 - Gerenciar ordem de serviço");//kaynnandeson
			System.out.println("6 - Histórico de vendas");//kaynnandeson
			System.out.println("0 - Sair");
			System.out.println("---------------------------------------");

			System.out.print("Escolha uma opção: ");

			int opcaoMenu = sc.nextInt();
			sc.nextLine();
		
			switch(opcaoMenu) {
			
			case 1:
				menuCadastrarProdutos(sc, estoque);
			break;
			
			case 2:
				menuExibirEstoque(sc, estoque);
			break;
			
			case 3:
				menuConsultarProduto(sc, estoque);
			break;
			
			case 4:
				menuRegistrarVenda(sc, estoque);
			break;
			
			case 5:
				menuGerenciarOS(sc, gerenciadorOS);
			break;

			case 6:
				menuHistoricoVendas(estoque);
			break;
			
			case 0:
				executando = false;
				System.out.println();
				System.out.println("Encerrando o sistema...");
				System.out.println("Sistema encerrado.");
			break;
			
			default:
				System.out.println("Opção inválida.");
		}
	}
		
		sc.close();
	}
}
