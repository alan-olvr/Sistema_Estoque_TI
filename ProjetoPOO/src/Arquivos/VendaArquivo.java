package Arquivos;

import ClassesBase.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class VendaArquivo {
	private static final String CAMINHO = "dados/vendas/vendas.txt";
	private static final String PASTA = "dados/vendas";

    public static void salvar(RegistroVenda v) {
    	
    	new File(PASTA).mkdirs();
    	boolean novoArquivo = !new File(CAMINHO).exists();
    	
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO, true))) {
        	
        	if(novoArquivo) {
        		bw.write("# VENDAS");
                bw.newLine();
                bw.write("# FORMATO:");
                bw.newLine();
                bw.write("# DATA; COD_PRODUTO ; QTD; VALOR_TOTAL");
                bw.newLine();
        	}
        	
            bw.write(String.join(";",
                    v.getDataHora().toString(),
                    v.getProduto().getCodigo(),
                    String.valueOf(v.getQuantidadeVendida()),
                    String.valueOf(v.getValorTotal())
            ));
            bw.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar venda.");
        }
    }

    public static List<String[]> carregar() {
		List<String[]> dados = new ArrayList<>();
		File arq = new File(CAMINHO);
		if (!arq.exists()) return dados;

		try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				if(linha.startsWith("#") || linha.isBlank()) continue;
				dados.add(linha.split(";"));
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar vendas.");
		}
		return dados;
	}
}
