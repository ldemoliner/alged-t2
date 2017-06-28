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
	        
//	       ArrayList<Aeroporto> teste1 = grafo.caminhoExclusivo("POA", "CGB","G3");
//	       if(teste1 != null){
//	    	   for (int i = 0; i < teste1.size()-1; i++){
//	    		   System.out.println(teste1.get(i).getCodigo() + " -> " + teste1.get(i+1).getCodigo() + " pelas airlines:");
//	    		   for(String s : grafo.cias(teste1.get(i).getCodigo(), teste1.get(i+1).getCodigo())){
//	    			   System.out.println(s);
//	    		   }
//	    	   }
//
////	    	   }
//	       }
	       System.out.println("jj, 800 -> " + grafo.autonomia(800, "JJ"));
	       System.out.println("jj, 3000 -> " + grafo.autonomia(3000, "JJ"));
	}

}
//AAG AAX