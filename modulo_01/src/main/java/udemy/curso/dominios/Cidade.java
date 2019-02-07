/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** Domínio de Cidade. */
@Entity
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "ESTADO_ID")
	private Estado estado;

	@JsonIgnore
	@Transient
	private Set<Endereco> enderecos = new HashSet<>();

	/** Construtor padrão. */
	public Cidade() {
	}

	/** @param nome
	 * @param estado */
	public Cidade(String nome, Estado estado) {
		this.nome = nome;
		this.estado = estado;
		estado.getCidades().add(this);
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
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
		Cidade other = (Cidade) obj;
		if (estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!estado.equals(other.estado)) {
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

	/** @return the estado */
	public Estado getEstado() {
		return estado;
	}

	/** @param estado the estado to set */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/** @return the enderecos */
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	/** @param enderecos the enderecos to set */
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
