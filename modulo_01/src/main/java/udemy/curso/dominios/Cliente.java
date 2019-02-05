/**
 * 
 */
package udemy.curso.dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import udemy.curso.dominios.enums.TipoCliente;
import udemy.curso.recursos.ExtratoraDeEnumIdentificavel;

/** Domínio de Cliente. */
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String email;

	private String numeroDoDocumento;

	@Column(name = "TIPO_CLIENTE_ID")
	private Integer idDoTipoCliente;

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	@Column(name = "NUMERO")
	private Set<String> telefones = new HashSet<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private Set<Endereco> enderecos = new HashSet<>();

	private List<Pedido> pedidos = new ArrayList<>();

	/** Construtor padrão. */
	public Cliente() {
	}

	/** @param nome
	 * @param email
	 * @param numeroDoDocumento
	 * @param tipo */
	public Cliente(String nome, String email, String numeroDoDocumento, TipoCliente tipo) {

		this.nome = nome;
		this.email = email;
		this.numeroDoDocumento = numeroDoDocumento;

		this.idDoTipoCliente = (Optional.ofNullable(tipo)
				.orElseThrow(() -> new IllegalArgumentException("O tipo do cliente não pode ser nulo.")))
						.getId();
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idDoTipoCliente == null) ? 0 : idDoTipoCliente.hashCode());
		result = prime * result + ((numeroDoDocumento == null) ? 0 : numeroDoDocumento.hashCode());
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
		Cliente other = (Cliente) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (idDoTipoCliente == null) {
			if (other.idDoTipoCliente != null) {
				return false;
			}
		} else if (!idDoTipoCliente.equals(other.idDoTipoCliente)) {
			return false;
		}
		if (numeroDoDocumento == null) {
			if (other.numeroDoDocumento != null) {
				return false;
			}
		} else if (!numeroDoDocumento.equals(other.numeroDoDocumento)) {
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

	/** @return the email */
	public String getEmail() {
		return email;
	}

	/** @param email the email to set */
	public void setEmail(String email) {
		this.email = email;
	}

	/** @return the numeroDoDocumento */
	public String getNumeroDoDocumento() {
		return numeroDoDocumento;
	}

	/** @param numeroDoDocumento the numeroDoDocumento to set */
	public void setNumeroDoDocumento(String numeroDoDocumento) {
		this.numeroDoDocumento = numeroDoDocumento;
	}

	/** @return the tipoCliente */
	public TipoCliente getTipoCliente() {
		return (TipoCliente) new ExtratoraDeEnumIdentificavel(TipoCliente.values()).de(this.idDoTipoCliente);
	}

	/** @param tipoCliente the tipoCliente to set */
	public void setTipoCliente(TipoCliente tipoCliente) {

		this.idDoTipoCliente = (Optional.ofNullable(tipoCliente)
				.orElseThrow(() -> new IllegalArgumentException("O tipo do cliente não pode ser nulo.")))
						.getId();
	}

	/** @return the telefones */
	public Set<String> getTelefones() {
		return telefones;
	}

	/** @param telefones the telefones to set */
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	/** @return the enderecos */
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	/** @param enderecos the enderecos to set */
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/** @return the pedidos */
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	/** @param pedidos the pedidos to set */
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
