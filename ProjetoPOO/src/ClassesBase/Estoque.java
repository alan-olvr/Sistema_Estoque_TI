package ClassesBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Arquivos.*;
import java.time.LocalDateTime;

public class Estoque {
	
	private List<Produto> produtos = new ArrayList<>();
	private List<RegistroVenda> historicoVendas = new ArrayList<>();
	
	public Estoque() {
		this.produtos = ProdutoArquivo.carregar();
		this.historicoVendas = new ArrayList<>();
		
		for (String[] p : VendaArquivo.carregar()) {
			try {
	            Produto prod = buscarProdutoPorCodigo(p[1]);

	            historicoVendas.add(
	                new RegistroVenda(
	                    prod,
	                    Integer.parseInt(p[2]),
	                    Double.parseDouble(p[3]),
	                    LocalDateTime.parse(p[0])
	                )
	            );

	        } catch (IllegalStateException e) {
	           System.out.println(e.getMessage());
	        }
	    }
	}
	
	public List<Produto> getProdutos(){
		return produtos;
	}
	
	public List<RegistroVenda> getHistoricoVendas(){
		return historicoVendas;
	}
	
	//método que adiciona produtos
	public void adicionarProduto(Produto p) {
		if(p == null) {
			throw new IllegalArgumentException("Produto inválido.");
		}
		
		for(Produto prod: produtos) {
			if(prod.getCodigo().equals(p.getCodigo())) {
				throw new IllegalArgumentException("Código já cadastrado.");
			}
		}
		produtos.add(p);
		
		ProdutoArquivo.salvar(produtos);
	}
	//método secundário para evitar if-else extenso no for.
	private boolean correspondeTipo(Produto p, int opcao) {
		switch(opcao) {
		case 1:
			return p instanceof Hardware;
		case 2:
			return p instanceof Perifericos;
		case 3: 
			return p instanceof Software;
		case 4: 
			return p instanceof Dispositivo;
		default:
			throw new IllegalArgumentException("Opção inválida.");
		}
	}
	
	public void exibirEstoque(int opc) {
		
		int qtdTotal = 0;
		double vTotal = 0.0;
		boolean encontrou = false;
		
		System.out.println("-------- ESTOQUE --------");
		for(Produto p : produtos) {
			if(correspondeTipo(p, opc)) {
				System.out.println(p);
				qtdTotal += p.getQuantity();
				vTotal += p.getPrice() * p.getQuantity();
				encontrou = true;
			}
		}
		
		if(!encontrou) {
			System.out.println("Nenhum produto cadastrado nessa categoria.");
			return;
		}
		
		System.out.println();
		System.out.println("Quantidade total = " + qtdTotal);
		System.out.printf("Valor em estoque = R$ %.2f", vTotal);
	}
	
	public void registrarVendaNoHistorico(Produto p, int qtd) {
		double valorTotal = p.getPrice() * qtd;
		RegistroVenda registro = new RegistroVenda(p, qtd, valorTotal, LocalDateTime.now());
		historicoVendas.add(registro);
		VendaArquivo.salvar(registro);
	}
	
	public void exibirHistoricoVendas() {
		if (historicoVendas.isEmpty()) {
			System.out.println("Nenhuma venda realizada ainda.");
			return;
		}
		System.out.println("-------- HISTÓRICO DE VENDAS --------");
		List<RegistroVenda> copia = new ArrayList<>(historicoVendas);
		Collections.reverse(copia); 

		for (RegistroVenda reg : copia) {
			System.out.println(reg);
		}
	}
	
	public Produto buscarProdutoPorCodigo(String codigo) {
		if (codigo == null || codigo.isBlank()) {
			throw new IllegalArgumentException("O código do produto não pode ser vazio.");
		}
		
	    for (Produto p : produtos) {
	        if (p.getCodigo().equalsIgnoreCase(codigo)) {
	            return p;
	        }
	    }
	    
	    throw new IllegalStateException("Produto não encontrado.");
	}
	
	public void registrarVenda(String codigo, int qtd) {

		Produto p = buscarProdutoPorCodigo(codigo);

		if (!(p instanceof Metods)) {
	        throw new IllegalStateException("Produto não pode ser vendido.");
	    }

	    Metods v = (Metods) p;

	    if (!v.podeVender(qtd)) {
	        throw new IllegalStateException("Venda não permitida.");
	    }

	    v.vender(qtd);


	    // Registra no histórico
	    registrarVendaNoHistorico(p, qtd);
	    
	    ProdutoArquivo.salvar(produtos);
	}
}
