package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;
//import java.util.Comparator;

public class Grafo {
	private ArrayList<Aeroporto> aeroportos;
	private ArrayList<Rota> rotas;
	
	static class Vertice implements Comparable<Vertice>{
		private Aeroporto aero;
		private double dist;
		private Vertice pai;
		private boolean visitado;
		public Vertice(Aeroporto aero, double dist){
			super();
			this.aero = aero;
			this.dist = dist;
			this.visitado = false;
		}
		
		@Override
		public String toString() {
			return "Vertice [aero=" + aero + ", dist=" + dist + ", pai=" + pai + ", visitado=" + visitado + "]";
		}

		public Aeroporto getAeroporto(){
			return this.aero;
		}
		
		public void setAeroporto(Aeroporto a){
			this.aero = a;
		}
		
		public double getDistancia(){
			return this.dist;
		}
		
		public void setDistancia(double dist){
			this.dist = dist;
		}
		
		public Vertice getPai(){
			return this.pai;
		}
		
		public void setPai(Vertice pai){
			this.pai = pai;
		}
		
		public boolean isVisitado(){
			return this.visitado;
		}		
		
		public void visitar(){
			this.visitado = true;
		}
		
		@Override
		public int compareTo(Vertice v){
			if(this.dist < v.getDistancia()){
				return -1;
			}else if(this.dist > v.getDistancia()){
				return 1;
			}
			return 0;
		}
		
	}
	
	public Grafo(){
		aeroportos = new ArrayList<>();
		rotas = new ArrayList<>();
	}
	

	public void adicionar(Aeroporto a) {
		aeroportos.add(a);
	}

	public void CarregaDados() {
		//Path c1 = Paths.get("src", "airports.dat");
		Path c1 = FileSystems.getDefault().getPath("src", "airports.dat");
		BufferedReader leitor;
		try {
			
		 leitor = Files.newBufferedReader(c1, Charset.defaultCharset());
			
			String lAtual;
			leitor.readLine();

	       while ((lAtual = leitor.readLine()) != null){
	    	  
				Scanner sc = new Scanner(lAtual);
				sc.useDelimiter("[;\n]");
	
				String iataCode = sc.next();
				sc.next();
				sc.next();
				String airportName = sc.next();
				String identificador = sc.next();
				aeroportos.add(new Aeroporto(iataCode, airportName, identificador));
				sc.close();
	       	}	
				System.out.println("Aeroportos carregados");
			
	
		} catch (IOException e) {
			System.out.println(e);
		}
	
	}
	
	public void setRotas(){
		Path c1 = FileSystems.getDefault().getPath("src","routes.dat");

		BufferedReader leitor;

		try {
			leitor = Files.newBufferedReader(c1, Charset.defaultCharset());

			String lAtual;
			leitor.readLine();
			leitor.readLine();
			while ((lAtual = leitor.readLine()) != null) {
				double dist = 0;
				String codOrigem, codDestino, codCia;
				Scanner sc = new Scanner(lAtual);
				sc.useDelimiter("[;\n]");
				codCia = null;
				int cont = 0;

				//codigo = sc.next();
				codOrigem = sc.next();
				codDestino = sc.next();
				dist = Double.parseDouble(sc.next());
				codCia = sc.next();

				for (Aeroporto a : aeroportos) {
					if (codOrigem.equals(a.getCodigo())){
						a.addRotaSaida(new Rota(codCia, codOrigem, codDestino, dist));
					} else if (codDestino.equals(a.getCodigo())){
						a.addRotaChegada(new Rota(codCia, codOrigem, codDestino, dist));
					}
				}
				rotas.add(new Rota(codCia, codOrigem, codDestino, dist));
				sc.close();
			}
			System.out.println("Rotas carregadas");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public Aeroporto congestionamento(){
		Aeroporto maior = new Aeroporto("MERECO", "UM", "10");
		for(Aeroporto a : aeroportos){
			if (a.getRotasChegada().size() > maior.getRotasChegada().size())
				maior = a;
		}		
		return maior;
	}
	
	public ArrayList<Aeroporto> listarTodos() {
		return aeroportos;
	}
	
	
	
	
	public ArrayList<Aeroporto> rotaMenorCusto(String cod1, String cod2){
		Aeroporto a1 = buscarCodigo(cod1);
		Aeroporto a2 = buscarCodigo(cod2);
		
		if (a1 == null || a2 == null){
			System.out.println("Aeroportos não encontrados");
			return null;
		} else if (!a1.getIdentificador().equals(a2.getIdentificador())){
			System.out.println("Aeroportos precisam ser do mesmo país");
			return null;	
		}
		
		ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
		Vertice verticeCaminho = new Vertice(new Aeroporto(".",".","."), 0);
		Vertice atual = new Vertice(a1,0);
		Vertice vizinho = new Vertice(a2,Double.MAX_VALUE);
		ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();
		ArrayList<Aeroporto> result = new ArrayList<>();
		boolean debug = false;
		for (Aeroporto a : aeroportos) {
			if (a.getIdentificador().equals(a1.getIdentificador())){
			Vertice v = new Vertice(a,0);
			if (a.getCodigo().equals(a1.getCodigo())) {
				v.setDistancia(0);
			} else {
				v.setDistancia(Double.MAX_VALUE);
			}
			naoVisitados.add(v);
			}
		}
		
		naoVisitados.sort((v1, v2) ->Double.compare(v1.getDistancia(), v2.getDistancia()));
		
		
		while (!naoVisitados.isEmpty()) {
			atual = naoVisitados.get(0);
			for (Rota r : atual.getAeroporto().getRotasSaida()) {		
				for (Vertice v : naoVisitados){
					if (v.getAeroporto().getCodigo().equals(r.getDestino())){
						debug = true;
						vizinho = v;
						break ;
					}
				}
				
				if (!vizinho.isVisitado() && debug) {
					if (teste(r.getDestino(), a1.getIdentificador()) && vizinho.getDistancia() > (atual.getDistancia() + r.getDist())) {
						vizinho.setDistancia(atual.getDistancia() + r.getDist());
						vizinho.setPai(atual);
						if (vizinho.getAeroporto().getCodigo().equals(a2.getCodigo())) {
							menorCaminho.clear();
							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							while (verticeCaminho.getPai() != null) {
								menorCaminho.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();
								
							}
							menorCaminho.sort((v1, v2) ->Double.compare(v1.getDistancia(), v2.getDistancia()));			
							
						}
					}
				}
				debug =  false;
			}
			atual.visitar();
			naoVisitados.remove(0);
			naoVisitados.sort((v1, v2) ->Double.compare(v1.getDistancia(), v2.getDistancia()));
		}
		for (Vertice v : menorCaminho){
			result.add(v.getAeroporto());
		}
		return result;	
	}
	
	public ArrayList<Aeroporto> caminhoExclusivo(String cod1, String cod2, String cia){
		Aeroporto a1 = buscarCodigo(cod1);
		Aeroporto a2 = buscarCodigo(cod2);
		
		if (a1 == null || a2 == null){
			System.out.println("Aeroportos não encontrados");
			return null;
		}
		
		ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
		Vertice verticeCaminho = new Vertice(new Aeroporto(".",".","."), 0);
		Vertice atual = new Vertice(a1,0);
		Vertice vizinho = new Vertice(a2,Double.MAX_VALUE);
		ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();
		ArrayList<Aeroporto> result = new ArrayList<>();
		boolean debug = false;
		for (Aeroporto a : aeroportos) {
			Vertice v = new Vertice(a,0);
			if (a.getCodigo().equals(a1.getCodigo())) {
				v.setDistancia(0);
			} else {
				v.setDistancia(Double.MAX_VALUE);
			}
			naoVisitados.add(v);	
		}
		
		naoVisitados.sort((v1, v2) ->Double.compare(v1.getDistancia(), v2.getDistancia()));
		
		while (!naoVisitados.isEmpty()) {
			atual = naoVisitados.get(0);
			for (Rota r : atual.getAeroporto().getRotasSaida()) {		
				for (Vertice v : naoVisitados){
					if (v.getAeroporto().getCodigo().equals(r.getDestino()) && r.getCia().equals(cia)){
						debug = true;
						vizinho = v;
						break ;
					}
				}
				
				if (!vizinho.isVisitado() && debug) {
					if (teste(r.getDestino(), a1.getIdentificador()) && vizinho.getDistancia() > (atual.getDistancia() + r.getDist())) {
						vizinho.setDistancia(atual.getDistancia() + r.getDist());
						vizinho.setPai(atual);
						if (vizinho.getAeroporto().getCodigo().equals(a2.getCodigo())) {
							menorCaminho.clear();
							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							while (verticeCaminho.getPai() != null) {
								menorCaminho.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();
								
							}
							menorCaminho.sort((v1, v2) ->Double.compare(v1.getDistancia(), v2.getDistancia()));			
						}
					}
				}
				debug =  false;
			}
			atual.visitar();
			naoVisitados.remove(0);
			naoVisitados.sort((v1, v2) ->Double.compare(v1.getDistancia(), v2.getDistancia()));
		}
		for (Vertice v : menorCaminho){
			result.add(v.getAeroporto());
		}
		return result;	
	}
	
	public ArrayList<String> cias(String a1, String a2){
		ArrayList<String> result = new ArrayList<>();
		for (Rota r : buscarCodigo(a1).getRotasSaida()){
			if (r.getDestino().equals(a2))
				result.add(r.getCia());
		}		
		return result;
	}
	
	public boolean teste(String cod, String codPais){
		Aeroporto a = buscarCodigo(cod);
		return a.getIdentificador().equals(codPais);
	}
	
	public Aeroporto buscarCodigo(String codigo) {
		for (Aeroporto a : aeroportos) {
			if (codigo.equals(a.getCodigo()))
				return a;
		}
		return null; // não achamos!
	}
	
	public boolean autonomia(double dist, String cia){
		int total = 0;
		int pode = 0;
		for(Rota r : rotas){
			if(r.getCia().equals(cia)){
				total++;
				if(r.getDist() <= dist){
					pode++;
				}
			}
		}
		return pode >= 0.7*total;
	}
	
}