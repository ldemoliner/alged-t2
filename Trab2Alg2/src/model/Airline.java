package model;
import java.io.Serializable;

public class Airline implements Serializable {

	private static final long serialVersionUID = 6732261645516576922L;
	
	private String codigo;
	private String nome;
	private static int totalCias = 0;
	
	public Airline(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
		totalCias++;
	}
	
	@Override
	public String toString()
	{
		return nome + " ("+codigo+")";
	}
	
	public static int getTotalCias() {
		return totalCias;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}	
}