package model;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

	public static Scanner sc = new Scanner(System.in);
	public static GerPaises gerPais=new GerPaises();
	public static Grafo g= new Grafo();
	public static GerCias gerCias = new GerCias();
	
	public static void main(String[] args) {
		
			gerPais.carregaDados();	        
			gerCias.carregaDados();
	        g.CarregaDados();
	        System.out.println("Não travou, é que as rotas demoram um pouco pra carregar...");
	        g.setRotas(); 
	        menu();
	}
//	       ArrayList<Aeroporto> teste1 = grafo.caminhoExclusivo("POA", "CGB","G3");
//	       if(teste1 != null){
//	    	   for (int i = 0; i < teste1.size()-1; i++){
//	    		   System.out.println(teste1.get(i).getCodigo() + " -> " + teste1.get(i+1).getCodigo() + " pelas airlines:");
//	    		   for(String s : grafo.cias(teste1.get(i).getCodigo(), teste1.get(i+1).getCodigo())){
//	    			   System.out.println(s);
//	    		   }
//	    	   }
//
//	    	   }
//	       }
	       public static void menu(){
	       int choice = 1;
	       
	       while(choice != 0){
	    	   System.out.println("\n============================================  MENU  ============================================\n"+
	    			   "\nDigite a opção desejada:\n "+
	    			   "\n1 - Verificar a rota de menor custo, em termos de distância total, entre dois aeroportos"+
	    			   "\n2 - Verificar se uma companhia aérea realiza uma rota exclusiva entre dois aeroportos"+
	    			   "\n3 - Verificar a autonomia de voo de um avião"+
	    			   "\n4 - Verificar um aeroporto de um determinado país possui maiores chances de congestionamento\n"+
	    			   "\n================================================================================================");
	    	   		try{
	    	   		   choice = sc.nextInt();
	    	   		}
	    	   		catch (InputMismatchException e) {
	    				System.out.println("Opção inválida.\nEncerrando.");
	    	   		}
	    	   		switch (choice) {
					case 1:
						rotaMenorCusto();
						menu();
						break;
					case 2:
						caminhoExclusivo();
						menu();
						break;
					case 3:
						autonomia();
						menu();
						break;
					case 4:
						congestionamento();
						menu();
						break;			
					default:
						System.out.println("Opção inválida.");
						break;
					}
	       		}
	       }
	    	   		
	    	public static void rotaMenorCusto(){
	    		try{
	    			System.out.println("\nDigite o código do primeiro aeroporto:");
	    			String a1=sc.next();
	    			System.out.println("\nDigite o código do segundo aeroporto:");
	    			String a2=sc.next();
	    			g.rotaMenorCusto(a1, a2);
	    		}
	    		catch (InputMismatchException e) {
	    			System.out.println("\nInformação inválida:");
	    		}
	    	}
	    	
			public static void caminhoExclusivo(){
				try{
					System.out.println("\nDigite o nome de uma companhia aérea:");
					String nome = sc.next();
					
					g.caminhoExclusivo(nome,"HTI","HID");
				} catch (InputMismatchException e) {
					System.out.println("\nInformação inválida:");
				} catch (Exception e) {
					System.out.println("\nOcorreu um erro:\n" + e.getMessage());
				}
			}
			
			public static void autonomia(){
				try{
					System.out.println("\nDigite a distância:");
					double dist = sc.nextDouble();
					System.out.println("\nDigite o nome da companhia aérea:");
					String nome = sc.next();
					g.autonomia(dist,nome);
				}
				catch (InputMismatchException e) {
					System.out.println("\nInformação inválida:\n");
				}
			}
	       
			public static void congestionamento(){
				try{
					System.out.println("\n Digite o nome do país:");
					String pais = sc.next();
					g.congestionamento();
				}	
				catch (InputMismatchException e) {
					System.out.println("\nInformação inválida:");
				} catch (Exception e) {
					System.out.println("\nOcorreu um erro:\n" + e.getMessage());
				}
			}
}
//AAG AAX