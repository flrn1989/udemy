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
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/** Classe de domínio de Categoria. */
@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@JsonBackReference
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();

	/** Construtor padrão. */
	public Categoria() {
	}

	/** @param nome */
	public Categoria(String nome) {
		this.nome = nome;
	}

	/** @param nome
	 * @param produtos */
	public Categoria(String nome, List<Produto> produtos) {
		this(nome);

		this.produtos.addAll(produtos);

		for (Produto produto : produtos) {
			if (!produto.getCategorias().contains(this)) {
				produto.getCategorias().add(this);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Categoria other = (Categoria) obj;
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

	/** @return the produtos */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/** @param produtos the produtos to set */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
