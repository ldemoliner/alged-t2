package model;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		
			GerPaises gerPais=new GerPaises();
	        Grafo grafo = new Grafo();    
			GerCias gerCias = new GerCias();
			gerPais.carregaDados();	        
			gerCias.carregaDados();
	        grafo.CarregaDados();
	        System.out.println("não travou, é q as rotas demoram um pouco pra carregar...");
	        grafo.setRotas(); 
	        
//	        Aeroporto teste = grafo.congestionamento();
//	        System.out.println(teste.getNome());
	        
	       ArrayList<Aeroporto> teste1 = grafo.rotaMenorCusto("HTI", "MEL");
	       if(teste1 != null){
	    	   for(Aeroporto a : teste1){
	    		   System.out.println(a.getCodigo() + ", ");
	    	   }
	       }
	}

}
//AAG AAX