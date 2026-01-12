package ClassesBase;

public class Software extends Produto implements Metods {
	
	String licenca;
	String SO;
	
	public Software(String codigo, String nome, double price, int quantity, String licenca, String SO) {
		super(codigo, nome, price, quantity);
		this.licenca = licenca;
		this.SO = SO;
	}
	
	public String getLicenca() {
		return this.licenca;
	}
	
	public String getSO() {
		return this.SO;
	}
	
	@Override
    public boolean podeVender(int qtd) {
       return qtd > 0; // licença ilimitada
    }

	@Override
    public void vender(int qtd) {
        // NÃO reduz estoque
    }

	@Override
	public double valorEstoque() {
		return super.quantity * super.price;
	}
	
	@Override
	public String toString() {
		return String.format("Nome: %s%nCódigo: %s%nValor: %.2f%nLicença: %s%nSistema Operacional: %s", 
				super.nome, super.getCodigo(), super.price, this.licenca, this.SO);
	}
}
