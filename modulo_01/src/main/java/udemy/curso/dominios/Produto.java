/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/** Classe para o domínio Produto. */
@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Double preco;

	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
			joinColumns = {
					@JoinColumn(name = "PRODUTO_ID") },
			inverseJoinColumns = {
					@JoinColumn(name = "CATEGORIA_ID") })
	private List<Categoria> categorias = new ArrayList<>();

	/** Construtor padrão. */
	public Produto() {
	}

	/** @param nome
	 * @param preco */
	public Produto(String nome, Double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	/** @param nome
	 * @param preco
	 * @param categorias */
	public Produto(String nome, Double preco, List<Categoria> categorias) {
		this(nome, preco);

		this.categorias.addAll(categorias);

		for (Categoria categoria : categorias) {
			if (!categoria.getProdutos().contains(this)) {
				categoria.getProdutos().add(this);
			}
		}
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
	public List<Categoria> getCategorias() {
		return categorias;
	}

	/** @param categorias the categorias to set */
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
