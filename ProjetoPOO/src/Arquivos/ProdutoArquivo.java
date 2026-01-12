package Arquivos;

import ClassesBase.*;
import java.io.*;
import java.util.*;

public class ProdutoArquivo {
	
	 private static final String CAMINHO = "dados/produtos/produtos.txt";
	 private static final String PASTA = "dados/produtos";

    public static void salvar(List<Produto> produtos) {
    	new File(PASTA).mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO))) {
        	
        	bw.write("# PRODUTOS");
            bw.newLine();
            bw.write("# FORMATO:");
            bw.newLine();
            bw.write("# TIPO; CODIGO; NOME; PRECO; QTD; ATRIBUTOS_ESPECIFICOS");
            bw.newLine();
        	
            for (Produto p : produtos) {
                bw.write(serialize(p));
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar produtos.");
        }
    }

	    public static List<Produto> carregar() {
	        List<Produto> produtos = new ArrayList<>();

	        File arq = new File(CAMINHO);
	        if (!arq.exists()) return produtos;

	        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

	            String linha;
	            while ((linha = br.readLine()) != null) {
	            	if(linha.startsWith("#") || linha.isBlank()) {
	            		continue;
	            	}
	            	
	                produtos.add(deserialize(linha));
	            }

	        } catch (IOException e) {
	            throw new RuntimeException("Erro ao carregar produtos.");
	        }

	        return produtos;
	    }

	    private static String serialize(Produto p) {

	        if (p instanceof Hardware h) {
	            return String.join(";",
	                    "HARDWARE", h.getCodigo(), h.getNome(),
	                    String.valueOf(h.getPrice()),
	                    String.valueOf(h.getQuantity()),
	                    h.getFabricante(),
	                    String.valueOf(h.getGarantiaMeses())
	            );
	        }

	        if (p instanceof Perifericos pf) {
	            return String.join(";",
	                    "PERIFERICO", pf.getCodigo(), pf.getNome(),
	                    String.valueOf(pf.getPrice()),
	                    String.valueOf(pf.getQuantity()),
	                    pf.getTipo(),
	                    String.valueOf(pf.getGarantiaMeses())
	            );
	        }

	        if (p instanceof Software s) {
	            return String.join(";",
	                    "SOFTWARE", s.getCodigo(), s.getNome(),
	                    String.valueOf(s.getPrice()),
	                    String.valueOf(s.getQuantity()),
	                    s.getLicenca(),
	                    s.getSO()
	            );
	        }

	        if (p instanceof Dispositivo d) {
	            return String.join(";",
	                    "DISPOSITIVO", d.getCodigo(), d.getNome(),
	                    String.valueOf(d.getPrice()),
	                    String.valueOf(d.getQuantity()),
	                    String.valueOf(d.getGarantiaDisp()),
	                    d.getTipo(),
	                    d.getMarca()
	            );
	        }

	        throw new IllegalArgumentException("Tipo desconhecido.");
	    }

	    private static Produto deserialize(String linha) {

	        String[] p = linha.split(";");

	        return switch (p[0]) {
	            case "HARDWARE" ->
	                new Hardware(p[1], p[2],
	                        Double.parseDouble(p[3]),
	                        Integer.parseInt(p[4]),
	                        p[5],
	                        Integer.parseInt(p[6]));

	            case "PERIFERICO" ->
	                new Perifericos(p[1], p[2],
	                        Double.parseDouble(p[3]),
	                        Integer.parseInt(p[4]),
	                        p[5],
	                        Integer.parseInt(p[6]));

	            case "SOFTWARE" ->
	                new Software(p[1], p[2],
	                        Double.parseDouble(p[3]),
	                        Integer.parseInt(p[4]),
	                        p[5], p[6]);

	            case "DISPOSITIVO" ->
	                new Dispositivo(p[1], p[2],
	                        Double.parseDouble(p[3]),
	                        Integer.parseInt(p[4]),
	                        Integer.parseInt(p[5]),
	                        p[6], p[7]);

	            default -> throw new IllegalArgumentException("Linha inválida.");
	        };
	    }
}
