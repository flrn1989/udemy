/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/** Domínio do Endereço. */
@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "CIDADE_ID")
	private Cidade cidade;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	/** Construtor padrão. */
	public Endereco() {
	}

	/** @param logradouro
	 * @param numero
	 * @param complemento
	 * @param bairro
	 * @param cep
	 * @param cidade */
	public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null) {
				return false;
			}
		} else if (!bairro.equals(other.bairro)) {
			return false;
		}
		if (cep == null) {
			if (other.cep != null) {
				return false;
			}
		} else if (!cep.equals(other.cep)) {
			return false;
		}
		if (cidade == null) {
			if (other.cidade != null) {
				return false;
			}
		} else if (!cidade.equals(other.cidade)) {
			return false;
		}
		if (complemento == null) {
			if (other.complemento != null) {
				return false;
			}
		} else if (!complemento.equals(other.complemento)) {
			return false;
		}
		if (logradouro == null) {
			if (other.logradouro != null) {
				return false;
			}
		} else if (!logradouro.equals(other.logradouro)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
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

	/** @return the logradouro */
	public String getLogradouro() {
		return logradouro;
	}

	/** @param logradouro the logradouro to set */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/** @return the numero */
	public String getNumero() {
		return numero;
	}

	/** @param numero the numero to set */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/** @return the complemento */
	public String getComplemento() {
		return complemento;
	}

	/** @param complemento the complemento to set */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/** @return the bairro */
	public String getBairro() {
		return bairro;
	}

	/** @param bairro the bairro to set */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/** @return the cep */
	public String getCep() {
		return cep;
	}

	/** @param cep the cep to set */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/** @return the cidade */
	public Cidade getCidade() {
		return cidade;
	}

	/** @param cidade the cidade to set */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	/** @return the cliente */
	public Cliente getCliente() {
		return cliente;
	}

	/** @param cliente the cliente to set */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
