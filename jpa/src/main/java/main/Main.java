package main;
import dao.ProdutoDAO;
import model.Produto;
import java.util.Scanner;

public class Main {
	
	public static void main(String[]args) {
		//Instanciando objetos
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		Scanner scn = new Scanner(System.in);
		//Definição da variável de opção
		String continuar ="sim";
		
		//Se for digitado sim ou SIM vai continuar o loop do while
		while(continuar.equalsIgnoreCase("sim")) {
			Produto produto = new Produto();
			System.out.println("Digite o nome do produto: ");
			produto.setNome(scn.nextLine());
			
			System.out.println("Digite o preço do produto: ");
			produto.setPreco(scn.nextDouble());
			scn.nextLine();
			
			produtoDAO.criar(produto);//Chamada do método que executa a criação do produto no banco de dados
			
			System.out.println("Deseja adicionar outro produto ? (sim/não): ");
			continuar = scn.nextLine();			
		}
		
		//Lista dos produtos presentes no banco de dados
		System.out.println("\nLista de produtos no banco de dados");
		//Chamada do método que lista os produtos presentes no banco de dados
		produtoDAO.listarTodos().forEach(p -> System.out.println(p.getNome() + " - " + p.getPreco()));
		
		continuar = "";
		
		//do-while para fazer o loop de atualização de algum produto
		do {
			Produto produto = new Produto();
			System.out.println("Deseja atualizar algum produto ? (sim/não): ");
			continuar = scn.nextLine();
			
			if(continuar.equalsIgnoreCase("sim")) {
				System.out.println("Digite o ID do produto que deseja atualizar:");
				produto.setId(scn.nextLong());
				scn.nextLine();//Limpa o buffer			
				System.out.println("Digite o novo nome do produto: ");
				produto.setNome(scn.nextLine());
				
				System.out.println("Digite o novo preço do produto: ");
				produto.setPreco(scn.nextDouble());
				
				produtoDAO.atualizar(produto);//Chamada do método que realiza a atualização do produto no banco de dados
				scn.nextLine();
			}
		}while(continuar.equalsIgnoreCase("sim"));
		
		//do-while para fazer a exclusão de algum objeto
		do {
			Produto produto = new Produto();
			System.out.println("Deseja excluir algum produto ? (sim/não): ");
			continuar = scn.nextLine();
			
			if(continuar.equalsIgnoreCase("sim")) {
				System.out.println("Digite o ID do produto que deseja excluir:");
				produto.setId(scn.nextLong());
				
				produtoDAO.excluir(produto.getId());//chamada do método que faz a exclusão do produto no banco de dados
			}
			scn.nextLine();
		}while(continuar.equalsIgnoreCase("sim"));
		
		
		
		scn.close();
		
		
	}

}
