package ClassesBase;

public class Hardware extends Produto implements Metods{
	
	String fabricante;
	int garantiaMeses;
	
	public Hardware(String codigo, String nome, double price, int quantity, String fabricante, int garantiaMeses) {
		super(codigo, nome, price, quantity);
		this.fabricante = fabricante;
		this.garantiaMeses = garantiaMeses;
	}
	
	public String getFabricante() {
		return this.fabricante;
	}
	
	public int getGarantiaMeses() {
		return this.garantiaMeses;
	}
	
	@Override
	public boolean podeVender(int qtd) {
        return qtd > 0 && qtd <= quantity;
    }
	
	@Override 
	public void vender(int qtd) {
		if (!podeVender(qtd)) {
			throw new IllegalStateException("Estoque insuficiente.");
	    }
		reduzirEstoque(qtd);
	}
	
	@Override
	public double valorEstoque() {
		return super.quantity * super.price;
	}
	
	
	@Override
	public String toString() {
		return String.format("Nome -> %s%nCódigo -> %s%nValor em estoque -> %.2f%nGarantia (em meses) -> %d%nFabricante -> %s"
				,super.nome, super.getCodigo(), valorEstoque(), this.garantiaMeses, this.fabricante);
	}
	
	
}
