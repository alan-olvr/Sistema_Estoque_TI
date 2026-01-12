package Arquivos;

import ClassesBase.*;
import java.io.*;
import java.util.*;

public class OrdemServicoArquivo {
	
	private static final String CAMINHO = "dados/ordens_servico/ordens_servico.txt";
	private static final String PASTA = "dados/ordens_servico";

    public static void salvar(List<OrdemServico> lista) {
    	
    	new File(PASTA).mkdirs();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO))) {
        	
        	bw.write("# ORDEM_SERVICO");
            bw.newLine();
            bw.write("# FORMATO:");
            bw.newLine();
            bw.write("# ID; CLIENTE ; STATUS; VALOR");
            bw.newLine();
            
            for (OrdemServico os : lista) {
                bw.write(String.join(";",
                        String.valueOf(os.getId()),
                        os.getNomeCliente(),
                        os.getStatus().name(),
                        String.valueOf(os.getValorServico())
                ));
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar OS.");
        }
    }
    
    public static List<OrdemServico> carregar() {
		List<OrdemServico> lista = new ArrayList<>();
		File arq = new File(CAMINHO);
		if (!arq.exists()) return lista;

		try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				if (linha.startsWith("#") || linha.isBlank()) continue;
				String[] p = linha.split(";");
				OrdemServico os = new OrdemServico(p[1], "");
				os.definirValor(Double.parseDouble(p[3]));
				lista.add(os);
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar OS.");
		}
		return lista;
	}
}
