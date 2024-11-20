package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Para garantir que a classe represente uma tabela no banco de dados, é
 * adicionada a anotação "@Entity" que indica que o JPA será responsável por
 * gerenciar a classe.
 */
@Entity
public class Produto {
	/*
	 * Antes de declarar os atributos da classe é adicionado o identificador "@Id"
	 * que serve para definir a chave primária da entidade, de forma que, com a
	 * utilização do "@GeneratedValue", indica que o id será gerado automaticamente
	 * pelo banco de dados.
	 * 
	 * Logo em seguida são definidos os atributos privados da classe, que serão utilizados
	 * para criar objetos da classe.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	
	/* Getters e Setters para retornar e atribuir os valores
	 * das variáveis privadas da classe.
	 */
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
