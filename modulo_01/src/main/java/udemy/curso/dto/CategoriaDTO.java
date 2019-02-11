/**
 * 
 */
package udemy.curso.dto;

import udemy.curso.dominios.Categoria;
import udemy.curso.interfaces.DTO;

/** DTO para o domínio Categoria. */
public final class CategoriaDTO implements DTO {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	/** Construtor. */
	public CategoriaDTO() {
	}

	/** Construtor a partir do domínio. */
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	/** {@inheritDoc} */
	@Override
	public Integer getId() {
		return id;
	}

	/** @return the nome */
	public String getNome() {
		return nome;
	}

	/** @param nome the nome to set */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** @param id the id to set */
	public void setId(Integer id) {
		this.id = id;
	}

}
