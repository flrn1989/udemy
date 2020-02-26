/**
 * 
 */
package udemy.curso.dto;

import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import udemy.curso.dominios.Categoria;
import udemy.curso.interfaces.DTO;

/** DTO para o domínio Categoria. */
public final class CategoriaDTO implements DTO<Categoria> {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório.")
	@Size(min = 4,
			max = 80,
			message = "A Categoria deve ter nome de tamanho entre 4 e 80 caracteres.")
	private String nome;

	/** Construtor. */
	public CategoriaDTO() {
	}

	/** Construtor a partir do domínio. */
	public CategoriaDTO(Categoria categoria) {
		this.nome = categoria.getNome();
	}

	/** {@inheritDoc} */
	@Override
	public Categoria paraDominio() {
		return new Categoria(this);
	}

	/** {@inheritDoc} */
	@Override
	public Categoria preencher(Categoria dominio) {

		if (Objects.isNull(dominio)) {
			return paraDominio();
		}

		dominio.setNome(Optional.ofNullable(this.getNome())
				.orElse(dominio.getNome()));

		return dominio;
	}

	/** @return the nome */
	public String getNome() {
		return nome;
	}

	/** @param nome the nome to set */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
