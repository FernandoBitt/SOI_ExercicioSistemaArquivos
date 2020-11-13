package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController {

	public ArquivosController() {
		super();
	}

	static String linhaEncontrada = ""; // vari�vel static pois o m�todo verificaRegistro � booleano,
										// n�o podendo retornar a linha e tamb�m � chamado em outro m�todo.

	@Override
	public void verificaDirTemp() throws IOException {
		File dir = new File("C:\\TEMP");

		if (dir.mkdir()) {
			System.out.println("Diret�rio Criado");
		} else {
			System.out.println("Diret�rio j� existente");
		}

		File arq = new File("C:\\TEMP", "Planilha.csv");
		if (!arq.exists()) {
			String conteudo = "Codigo; Nome; Email" + "\r\n";
			FileWriter fileWriter = new FileWriter(arq);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
			System.out.println("Arquivo (Planilha.csv) Criado");
		} else {
			System.out.println("Arquivo (Planilha.csv) j� existente");
		}
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File("C:\\TEMP", arquivo);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) { // procurando End Of File (EOF)
				String[] compara = linha.split(";");
				if (compara[0].equals(String.valueOf(codigo))) {
					linhaEncontrada = linha;
					buffer.close();
					leitor.close();
					fluxo.close();
					return true;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inv�lido");
		}
		return false;
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		File dir = new File("C:\\TEMP");
		File arq = new File("C:\\TEMP", arquivo);
		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists()) {
				if (verificaRegistro(arquivo, codigo)) {
					String[] linhaSeparado = linhaEncontrada.split(";");
					System.out.println("C�digo: " + linhaSeparado[0] + "\nNome: " + linhaSeparado[1] + "\nEmail: "
							+ linhaSeparado[2]);
				} else {
					System.out.println("C�digo n�o encontrado");
				}
			}
		} else {
			throw new IOException("Diret�rio inv�lido");
		}
	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		File dir = new File("C:\\TEMP");
		File arq = new File("C:\\TEMP", arquivo);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			if (!verificaRegistro(arquivo, codigo)) {
				String conteudo = codigo + ";" + nome + ";" + email + "\r\n";
				FileWriter fileWriter = new FileWriter(arq, existe);
				PrintWriter print = new PrintWriter(fileWriter);
				print.write(conteudo);
				print.flush();
				print.close();
				fileWriter.close();
			} else {
				System.out.println("C�digo j� existente");
			}
		} else {
			throw new IOException("Diret�rio inv�lido");
		}
	}
}
