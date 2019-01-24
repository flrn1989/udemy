/**
 * 
 */
package udemy.curso.categoria;

import java.io.Serializable;

/** Classe de domínio de Categoria. */
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;

	/** Construtor padrão. */
	public Categoria() {
	}

	/** @param id
	 * @param nome */
	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	/** @return the id */
	public Long getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(Long id) {
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

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

}
