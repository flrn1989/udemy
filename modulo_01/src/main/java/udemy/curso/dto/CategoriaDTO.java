/**
 * 
 */
package udemy.curso.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import udemy.curso.dominios.Categoria;
import udemy.curso.interfaces.DTO;

/** DTO para o domínio Categoria. */
public final class CategoriaDTO implements DTO<Categoria> {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "A Categoria deve possuir um nome.")
	@Size(min = 4,
			max = 80,
			message = "A Categoria deve ter nome de tamanho entre 4 e 80 caracteres.")
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
	public Categoria paraDominio() {
		return new Categoria(this);
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
