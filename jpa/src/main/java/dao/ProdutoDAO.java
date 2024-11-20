package dao;

import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoDAO {
	/*
	 * Atributo do tipo "EntityManagerFactory" que gerencia os objetos do tipo
	 * "EntityManager", assim, conectando a aplicação ao banco de dados.
	 */
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("produtoPU");
	
	/*
	 * Para adiconar um produto no banco de dados, primeiramente é criada uma
	 * instância "EntityManager" que será usada para interagir com o banco de dados.
	 * Logo em seguida o "em.getTransaction().begin()" inicia a transação e para em fim
	 * adicionar o produto no banco de dados o "persist" insere o objeto vindo da classe
	 * "Produto", assim para finalizar o "em.getTransaction().commit()" salva as alterações
	 * e fecha o "EntityManager" para que esteja disponível para os outros métodos.
	 */
	public void criar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		em.close();
	}
	
	/*
	 * Para conseguir ler os produtos no banco de dados, primeiramente é criada uma
	 * instância "EntityManager" que será usada para interagir com o banco de dados.
	 * Logo em seguida, através do "em.find()" o método faz uma busca no banco de dados
	 * para encontrar o produto, e para isso é informado que será utilizado o "id" do objeto
	 * criado na classe "Produto" como parâmetro para a busca.
	 * Por fim o "EntityManager" é fechado para estar disponível para os outros métodos e
	 * é retornado o dado encontrado no banco.
	 */
	public Produto ler(Long id) {
		EntityManager em = emf.createEntityManager();
		Produto produto = em.find(Produto.class, id);
		em.close();
		return produto;
	}
	
	/*
	 * Para listar todos os produtos armazenados no banco de dados, primeiramente é criada
	 * uma instância "EntityManager" que será usada para interagir com o banco de dados.
	 * Logo após isso, é declarada um "List" que atribui valor a variável de acrodo com o
	 * comando de "select" que foi executado no banco de dados, assim, retornando os dados
	 * dos produtos armazenados.
	 * Por fim o "EntityManager" é fechado para estar disponível para os outros métodos.
	 */
	public List<Produto> listarTodos(){
		EntityManager em = emf.createEntityManager();
		List<Produto> produtos = em.createQuery("FROM Produto", Produto.class).getResultList();
		em.close();
		return produtos;
	}
	
	/*
	 * Para atualizar um produto no banco de dados, semelhante ao método de inserir,
	 * primeiramente é criada uma instância "EntityManager" que será usada para interagir
	 * com o banco de dados. Logo em seguida o "em.getTransaction().begin()" inicia a
	 * transação e para em fim adicionar o produto no banco de dados o "persist" insere
	 * o objeto vindo da classe "Produto", assim para finalizar o "em.getTransaction().commit()"
	 * salva as alterações e fecha o "EntityManager" para que esteja disponível para os
	 * outros métodos.
	 */
	public void atualizar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(produto);
		em.getTransaction().commit();
		em.close();
	}
	
	/*
 	 * Para excluir um produto no banco de dados, primeiramente é criada uma
	 * instância "EntityManager" que será usada para interagir com o banco de dados.
	 * Logo em seguida acontece a busca pelo produto que possui o "id" informado para busca,
	 * com isso, uma condição "if" verifica se há um produto com o "id" informado no banco
	 * de dados, caso haja, produto é removido através do "em.remove()" e por fim o
	 * "EntityManager" é fechado para que esteja disponível para os outros métodos.
	 */
	public void excluir(Long id) {
		EntityManager em = emf.createEntityManager();
		Produto produto = em.find(Produto.class, id);
		if(produto != null) {
			em.getTransaction().begin();
			em.remove(produto);
			em.getTransaction().commit();
		}
		em.close();
	}
}