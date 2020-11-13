package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {

		IArquivosController arqCont = new ArquivosController();

		int opcao = 0;
		while (opcao != 9) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite 1: para criar o diretorório C:\\TEMP"
					+ "\nDigite 2: para imprimir o cadastro" + "\nDigite 3: para inserir cadastro" + "\nDigite 9: para sair"));

			switch (opcao) {
			case 1:
				try {
					arqCont.verificaDirTemp();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				String arquivoBusca = "Planilha.csv";
				int codigoBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código"));
				try {
					arqCont.imprimeCadastro(arquivoBusca, codigoBusca);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				String arquivo = "Planilha.csv";
				int codigo= Integer.parseInt(JOptionPane.showInputDialog("Digite um número do código"));
				String nome = JOptionPane.showInputDialog("Digite um nome");
				String email = JOptionPane.showInputDialog("Digite um email");
				
				try {
					arqCont.insereCadastro(arquivo, codigo, nome, email);
				} catch (IOException e) {
					e.printStackTrace();
				
				}	
				break;
			
			case 9:
				System.out.println("Saindo");
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida");
				break;
			}
		}
		
	}
}
