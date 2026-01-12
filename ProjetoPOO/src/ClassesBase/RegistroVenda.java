package ClassesBase;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

public class RegistroVenda {
	
	 private Produto produto; 
	 private int quantidadeVendida; 
	 private double valorTotal; 
	 private LocalDateTime dataHora;

	public RegistroVenda(Produto produto, int quantidadeVendida, double valorTotal, LocalDateTime data) {
			this.produto = produto;
			this.quantidadeVendida = quantidadeVendida;
			this.valorTotal = valorTotal;
			this.dataHora = data; 
	}
	
	public double getValorTotal() {
		return this.valorTotal;
	}
	
	public Produto getProduto() {
		return this.produto;
	}
	
	public LocalDateTime getDataHora() {
		return this.dataHora;
	}
	
	public int getQuantidadeVendida() {
		return this.quantidadeVendida;
	}

	@Override
	public String toString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return String.format("Data: %s | Produto: %s | Qtd: %d | Total: R$ %.2f",
			this.dataHora.format(formato), 
			this.produto.getCodigo(), 
			this.quantidadeVendida, 
			this.valorTotal);
	}
}
