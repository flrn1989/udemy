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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/** Domínio de Estado. */
@Entity
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@JsonBackReference
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();

	/** Construtor padrão. */
	public Estado() {
	}

	/** @param nome */
	public Estado(String nome) {
		this.nome = nome;
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

	/** @return the cidades */
	public List<Cidade> getCidades() {
		return cidades;
	}

	/** @param cidades the cidades to set */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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
		Estado other = (Estado) obj;
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
