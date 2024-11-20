package Main;

import dao.ProdutoDAO;
import model.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		/*
		 * Para executar os métodos na classe "Main" são criados dois objetos, primeiro
		 * um objeto da classe "produtoDAO" que será utilizado para acessar os métodos da
		 * classe DAO e assim manipular os dados.
		 * 
		 * Logo em seguida é criado um objeto "Scanner" para entrada de dados pelo terminal.
		 */
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Scanner scanner = new Scanner(System.in);

        /*
         * Para que funcione como um menu, é declarada um laço de repetição while, 
         * para que a tela de menu sempre retorne, até que o usuário escolha sair.
         */
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Inserir Produto");
            System.out.println("2. Encontrar Produto");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                /*
                 * Para enviar os dados de forma correta para o método em de forma correta,
                 * cada opção presente executa e pede os dados de acordo com oque é
                 * preciso, utilizando os getters e setters para atribuir e acessar os valores
                 * dos atributos privados na classe. 
                 */
                switch (opcao) {
                    case 1:
                        // Inserir Produto
                        Produto novoProduto = new Produto();
                        System.out.print("Digite o nome do produto: ");
                        novoProduto.setNome(scanner.nextLine());
                        System.out.print("Digite o preço do produto: ");
                        novoProduto.setPreco(scanner.nextDouble());
                        scanner.nextLine();
                        produtoDAO.criar(novoProduto);
                        System.out.println("Produto adicionado com sucesso!");
                        break;

                    case 2:
                        // Encontrar Produto
                        System.out.print("Digite o ID do produto a ser encontrado: ");
                        Long idBusca = scanner.nextLong();
                        scanner.nextLine();
                        Produto produtoEncontrado = produtoDAO.ler(idBusca);
                        if (produtoEncontrado != null) {
                            System.out.println("Produto encontrado:");
                            System.out.println("ID: " + produtoEncontrado.getId());
                            System.out.println("Nome: " + produtoEncontrado.getNome());
                            System.out.println("Preço: " + produtoEncontrado.getPreco());
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;

                    case 3:
                        // Atualizar Produto
                        System.out.print("Digite o ID do produto a ser atualizado: ");
                        Long idAtualizar = scanner.nextLong();
                        scanner.nextLine();
                        Produto produtoAtualizar = produtoDAO.ler(idAtualizar);
                        if (produtoAtualizar != null) {
                            System.out.print("Digite o novo nome do produto: ");
                            produtoAtualizar.setNome(scanner.nextLine());
                            System.out.print("Digite o novo preço do produto: ");
                            produtoAtualizar.setPreco(scanner.nextDouble());
                            scanner.nextLine();
                            produtoDAO.atualizar(produtoAtualizar);
                            System.out.println("Produto atualizado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;

                    case 4:
                        // Excluir Produto
                        System.out.print("Digite o ID do produto a ser excluído: ");
                        Long idExcluir = scanner.nextLong();
                        scanner.nextLine();
                        Produto produtoExcluir = produtoDAO.ler(idExcluir);
                        if (produtoExcluir != null) {
                            produtoDAO.excluir(idExcluir);
                            System.out.println("Produto excluído com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;

                    case 0:
                        // Sair
                        System.out.println("Saindo...");
                 	    System.out.println("\nLista de produtos no banco de dados: ");
                	    produtoDAO.listarTodos().forEach(p -> System.out.println(p.getNome() + " - " + p.getPreco()));
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }
}
