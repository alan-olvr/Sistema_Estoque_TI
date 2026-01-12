package ClassesBase;

public class Dispositivo extends Produto implements Metods{
	
	String tipo, marca;
	int garantiaDisp;
	
	public Dispositivo(String codigo, String nome, double price, int quantity, int garantia, String tipo, String marca) {
		super(codigo, nome, price, quantity);
		this.tipo = tipo;
		this.marca = marca;
		this.garantiaDisp = garantia;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public String getMarca() {
		return this.marca;
	}
	
	public int getGarantiaDisp() {
		return this.garantiaDisp;
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
		return String.format("Marca: %s%nDispositivo: %s%nCódigo: %s%nValor em estoque: %.3f%nGarantia: %d", 
				this.marca, super.nome, super.getCodigo(), valorEstoque(), this.garantiaDisp);
	}
}
