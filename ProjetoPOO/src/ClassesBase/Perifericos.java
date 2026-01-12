package ClassesBase;

public class Perifericos extends Produto implements Metods{
	
	String tipo;
	int garantiaMeses;
	
	public Perifericos(String codigo, String nome, double price, int quantity, String tipo, int garantia) {
		super(codigo, nome, price, quantity);
		this.tipo = tipo;
		this.garantiaMeses = garantia;
	}
	
	public String getTipo() {
		return this.tipo;
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
		return String.format("Nome: %s%nCódigo: %s%nValor em estoque: %.2f%nGarantia (em meses): %d%n", 
				super.nome, super.getCodigo(), valorEstoque(), this.garantiaMeses);
	}
}
