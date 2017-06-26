package model;
public class Pais implements  Comparable<Pais> {
	String codigo;
	String nome;

	public Pais(String codigo, String nome) {
		this.codigo = nome;
		this.nome = nome;

	}

	public String toString() {
		return "Pais [codigo=" + codigo + ", nome=" + nome + "]";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Pais p) {
		return codigo.compareTo(p.codigo);
	}

}