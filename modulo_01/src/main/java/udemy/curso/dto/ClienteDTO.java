/**
 * 
 */
package udemy.curso.dto;

import udemy.curso.dominios.Cliente;
import udemy.curso.interfaces.DTO;

/** DTO de Cliente. */
public class ClienteDTO implements DTO<Cliente> {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String email;

	private String numeroDoDocumento;

	private String tipoCliente;

	/** Construtor. */
	public ClienteDTO() {
	}

	/** Construtor pelo domínio. */
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.numeroDoDocumento = cliente.getNumeroDoDocumento();
		this.tipoCliente = cliente.getTipoCliente();
	}

	/** {@inheritDoc} */
	@Override
	public Cliente paraDominio() {
		return new Cliente(this);
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
		return tipoCliente;
	}

	/** @param tipoCliente the tipoCliente to set */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

}
