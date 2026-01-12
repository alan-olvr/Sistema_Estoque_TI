package ClassesBase;

public class OrdemServico {
	private static int contadorIds = 1;
	
	private int id;
	private String nomeCliente;
	private String descricaoProblema;
	private double valorServico;
	private StatusOS status; 

	public OrdemServico(String nomeCliente, String descricaoProblema) {
		this.id = contadorIds++; 
		this.nomeCliente = nomeCliente;
		this.descricaoProblema = descricaoProblema;
		this.status = StatusOS.ABERTA; 
		this.valorServico = 0.0;
	}
	
	public String getNomeCliente() {
		return this.nomeCliente;
	}
	
	public double getValorServico() {
		return this.valorServico;
	}
	
	public StatusOS getStatus() {
		return this.status;
	}

	public void avancarStatus() {
		if (status == StatusOS.CANCELADA || status == StatusOS.CONCLUIDA) {
			throw new IllegalStateException("Não é possível alterar o status desta OS.");
		}
		
		if(this.status == StatusOS.ABERTA) {
			this.status = StatusOS.EM_ANALISE;
		} else if (this.status == StatusOS.EM_ANALISE) {
			this.status = StatusOS.CONCLUIDA;
		}
	}

	public void cancelarOS() {
		this.status = StatusOS.CANCELADA;
	}

	public void definirValor(double valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("O valor do serviço não pode ser negativo.");
		}
		
		this.valorServico = valor;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("OS #%d | Cliente: %s | Status: %s | Valor: R$ %.2f | Defeito: %s",
				this.id, this.nomeCliente, this.status, this.valorServico, this.descricaoProblema);
	}
}
