package model;

public class Rota {
	private String cia;
	private String origem;
	private String destino;
	private double dist;
	
	public Rota(String cia, String origem, String destino, double dist) {
		this.cia = cia;
		this.origem = origem;
		this.destino = destino;
		this.dist = dist;		
	}	
	
//	@Override
//	public String toString()
//	{
//		return cia.getCodigo()+": "
//				+origem.getCodigo()+" -> "+destino.getCodigo()
//				+" ["+aeronave.getCodigo()+"]";
//	}
	
	
	public String getCia() {
		return cia;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public String getOrigem() {
		return origem;
	}
	
	public double getDist() {
		return dist;
	}
}