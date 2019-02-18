/**
 * 
 */
package udemy.curso.dominios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import udemy.curso.dominios.enums.TipoCliente;
import udemy.curso.dto.ClienteDTO;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;
import udemy.curso.util.ExtratoraDeEnum;

/** Domínio de Cliente. */
@Entity
public class Cliente implements Dominio {

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

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Endereco> enderecos = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
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

	/** @param dto */
	public Cliente(ClienteDTO dto) {
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.numeroDoDocumento = dto.getNumeroDoDocumento();
		this.telefones = dto.getTelefones();

		this.idDoTipoCliente = ExtratoraDeEnum
				.extrairDe(TipoCliente.values())
				.valorCom(dto.getTipoCliente())
				.getId();

		this.enderecos = dto.getEnderecos()
				.stream()
				.map(e -> new Endereco(e))
				.collect(Collectors.toSet());

		this.enderecos.stream()
				.forEach(e -> e.setCliente(this));
	}

	/** {@inheritDoc} */
	@Override
	public DTO<Cliente> paraDTO() {
		return new ClienteDTO(this);
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
	public String getTipoCliente() {
		return ExtratoraDeEnum
				.extrairDe(TipoCliente.values())
				.valorCom(this.idDoTipoCliente)
				.getDescricao();
	}

	/** @param tipoCliente the tipoCliente to set */
	public void setTipoCliente(TipoCliente tipoCliente) {

		this.idDoTipoCliente = (Optional.ofNullable(tipoCliente)
				.orElseThrow(() -> new IllegalArgumentException("O tipo do cliente não pode ser nulo.")))
						.getId();
	}

	/** @param descricaoDoTipoCliente to set */
	public void setTipoCliente(String descricaoDoTipoCliente) {

		this.idDoTipoCliente = ((TipoCliente) ExtratoraDeEnum
				.extrairDe(TipoCliente.values())
				.valorCom(descricaoDoTipoCliente))
						.getId();
	}

	/** @param idDoTipoCliente to set */
	public void setTipoCliente(Integer idDoTipoCliente) {
		this.idDoTipoCliente = idDoTipoCliente;
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
