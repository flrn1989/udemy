/**
 * 
 */
package udemy.curso.dto;

import java.util.HashSet;
import java.util.Set;

import udemy.curso.dominios.Categoria;
import udemy.curso.dominios.Produto;
import udemy.curso.interfaces.DTO;

/** DTO de Produto; */
public class ProdutoDTO implements DTO<Produto> {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Double preco;

	private Set<Categoria> categorias = new HashSet<>();

	/** Construtor. */
	public ProdutoDTO() {
	}

	/** Construtor do domínio. */
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.categorias = produto.getCategorias();
	}

	/** {@inheritDoc} */
	@Override
	public Produto paraDominio() {
		return new Produto(this);
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

}
