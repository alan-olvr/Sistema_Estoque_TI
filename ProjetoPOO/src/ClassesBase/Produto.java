package ClassesBase;

public abstract class Produto {
	
	//atributos específicos para os produtos.
	private final String codigo;
	protected String nome;
	protected double price;
	protected int quantity;
	
	//construtores
	public Produto(String codigo, String nome, double price, int quantity) {
		this.codigo = codigo;
		this.nome = nome;
		this.price = price;
		this.quantity = quantity;
	}
	
	//getter e setters
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//métodos concretos
	 protected void reduzirEstoque(int qtd) {
	        this.quantity -= qtd;
	 }
	
	//métodos abstratos
	public abstract double valorEstoque();
	
	public String toString() {
		return this.nome + this.codigo + this.price;
	}
}
