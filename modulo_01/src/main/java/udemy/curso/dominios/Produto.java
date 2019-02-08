/**
 * 
 */
package udemy.curso.dominios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import udemy.curso.interfaces.Dominio;

/** Classe para o domínio Produto. */
@Entity
public class Produto implements Dominio {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Double preco;

	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
			joinColumns = {
					@JoinColumn(name = "PRODUTO_ID") },
			inverseJoinColumns = {
					@JoinColumn(name = "CATEGORIA_ID") })
	private Set<Categoria> categorias = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ProdutoPedido> produtosPedidos = new HashSet<>();

	/** Construtor padrão. */
	public Produto() {
	}

	/** @param nome
	 * @param preco */
	public Produto(String nome, Double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	@JsonIgnore
	/** @return os pedidos deste produto. */
	public List<Pedido> getPedidos() {

		List<Pedido> pedidos = new ArrayList<>();

		for (ProdutoPedido produtoPedido : this.produtosPedidos) {
			pedidos.add(produtoPedido.getId().getPedido());
		}

		return pedidos;
	}

	/** @return the id */
	public Integer getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return the nome */
	public String getNome() {
		return nome;
	}

	/** @param nome the nome to set */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** @return the preco */
	public Double getPreco() {
		return preco;
	}

	/** @param preco the preco to set */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/** @return the categorias */
	public Set<Categoria> getCategorias() {
		return categorias;
	}

	/** @param categorias the categorias to set */
	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	/** @return the produtosPedidos */
	public Set<ProdutoPedido> getProdutosPedidos() {
		return produtosPedidos;
	}

	/** @param produtosPedidos the produtosPedidos to set */
	public void setProdutosPedidos(Set<ProdutoPedido> produtosPedidos) {
		this.produtosPedidos = produtosPedidos;
	}

}
