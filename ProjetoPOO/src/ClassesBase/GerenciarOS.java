package ClassesBase;

import java.util.List;
import java.util.ArrayList;
import Arquivos.*;

public class GerenciarOS {
	
	 private List<OrdemServico> listaOS = new ArrayList<>();
	 
	 public GerenciarOS() {
		 this.listaOS = OrdemServicoArquivo.carregar();
	 }
	 
	 public List<OrdemServico> getListaOS(){
		 return listaOS;
	 }
	 
	 public void criarOS(String cliente, String problema) {
	 	OrdemServico novaOS = new OrdemServico(cliente, problema);
	 	listaOS.add(novaOS);
	 	OrdemServicoArquivo.salvar(listaOS);
	 	System.out.println("OS #" + novaOS.getId() + " criada com sucesso!");
	 }

	 public void listarTodasOS() {
	 	if (listaOS.isEmpty()) {
	 		System.out.println("Nenhuma Ordem de Serviço cadastrada.");
	 		return;
	 	}
	 	System.out.println("----- LISTA DE ORDENS DE SERVIÇO -----");
	 	for (OrdemServico os : listaOS) {
	 		System.out.println(os);
	 	}
	 }

	 public OrdemServico buscarOS(int id) {
	 	for (OrdemServico os : listaOS) {
	 		if (os.getId() == id) {
	 			return os;
	 		}
	 	}
	 	return null;
	 }
}
