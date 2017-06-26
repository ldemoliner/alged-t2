package model;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//import com.cedarsoftware.util.io.JsonReader;
//import com.cedarsoftware.util.io.JsonWriter;

public class GerCias {
	private ArrayList<Airline> empresas;

	public GerCias() {
		empresas = new ArrayList<>();
	}

	public void adicionar(Airline cia) {
		empresas.add(cia);
	}

	public void carregaDados()  {
		Path c1 = FileSystems.getDefault().getPath("src", "airlines.dat");

		BufferedReader leitor;

		try {
			leitor = Files.newBufferedReader(c1, Charset.defaultCharset());

			String lAtual;
			leitor.readLine();
			while ((lAtual = leitor.readLine()) != null) {
				Scanner sc = new Scanner(lAtual);
				sc.useDelimiter("[;\n]");

				String codigo = sc.next();
				String nome = sc.next();
				empresas.add(new Airline(codigo, nome));
			}
			System.out.println("CIAS carregadas");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void gravaSerial() throws IOException {
		Path arq1 = Paths.get("airlines.ser");
		try (ObjectOutputStream oarq = new ObjectOutputStream(Files.newOutputStream(arq1))) {
			oarq.writeObject(empresas);
		}
	}

	public void carregaSerial() throws IOException, ClassNotFoundException {
		Path arq1 = Paths.get("airlines.ser");
		try (ObjectInputStream iarq = new ObjectInputStream(Files.newInputStream(arq1))) {
			empresas = (ArrayList<Airline>) iarq.readObject();
		}
		System.out.println("Total empresas: " + empresas.size());
	}

//	public void gravaJSON() throws IOException {
//		try (JsonWriter writer = new JsonWriter(new BufferedOutputStream(new FileOutputStream("airlines.json")))) {
//			writer.write(empresas);
//		}
//	}

	public ArrayList<Airline> listarTodas() {
		// ArrayList<CiaAerea> nova = new ArrayList<>();
		// for(CiaAerea cia: empresas)
		// nova.add(cia);
		// return nova;
		return new ArrayList<Airline>(empresas);
	}

	public Airline buscarCodigo(String codigo) {
		for (Airline c : empresas) {
			if (codigo.equals(c.getCodigo()))
				return c;
		}
		return null; // não achamos!
	}

	public Airline buscarNome(String nome) {
		for (Airline c : empresas) {
			if (nome.equals(c.getNome()))
				return c;
		}
		return null; // não achamos!
	}
}