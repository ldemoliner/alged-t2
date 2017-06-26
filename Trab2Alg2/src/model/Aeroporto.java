package model;

import java.util.ArrayList;

public class Aeroporto {
	private String codigo;
	private String nome;
	private String identificador;
	private ArrayList<Rota> rotasSaindo;
	private ArrayList<Rota> rotasChegando;
	//private Geo loc;

	
	public Aeroporto(String codigo, String nome,String identificador) {
		this.codigo = codigo;
		this.nome = nome;
	//	this.loc = loc;
		this.identificador=identificador;
		rotasSaindo = new ArrayList<>();
		rotasChegando = new ArrayList<>();
	}
	

	
	public String getCodigo() {
		return codigo;
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNome() {
		return nome;
	}
	
	public void addRotaSaida(Rota rota){
		this.rotasSaindo.add(rota);
	}
	
	public void addRotaChegada(Rota rota){	
		this.rotasChegando.add(rota);
	}
	
	
	public ArrayList<Rota> getRotasSaida(){
		return this.rotasSaindo;
	}
	
	public ArrayList<Rota> getRotasChegada(){
		return this.rotasChegando;
	}
//	public Geo getLocal() {
//		return loc;
//	}

	@Override
	public String toString() {
		return "Aeroporto [codigo=" + codigo + ", nome=" + nome + ", identificador=" + identificador
				+ "]";
	}
}