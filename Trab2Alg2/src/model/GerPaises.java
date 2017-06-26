package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GerPaises {

	private ArrayList<Pais> paises;

	public GerPaises() {
		paises = new ArrayList<>();
	}

	public void adicionar(Pais p) {
		paises.add(p);
	}

	public void ordenarPaises() {
		Collections.sort(paises);
	}

	public void carregaDados() {
		Path c1 = FileSystems.getDefault().getPath("src","countries.dat");

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
				paises.add(new Pais(codigo, nome));
				sc.close();
			}
			System.out.println("Países carregados");
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void ordenarDescricao() {

		paises.sort(Comparator.comparing(Pais::getCodigo));
	}

	public void ordenarCodigo() {

		paises.sort(Comparator.comparing(Pais::getNome));
	}

	public ArrayList<Pais> listarTodas() {
		return paises;
	}

	public Pais buscarCodigo(String codigo) {
		for (Pais p : paises) {
			if (codigo.equals(p.getCodigo()))
				return p;
		}
		return null; // não achamos!
	}
}