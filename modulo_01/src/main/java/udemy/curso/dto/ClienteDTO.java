/**
 * 
 */
package udemy.curso.dto;

import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import udemy.curso.dominios.Cliente;
import udemy.curso.interfaces.DTO;

/** DTO de Cliente. */
public class ClienteDTO implements DTO<Cliente> {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório.")
	@Size(min = 8, max = 160)
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório.")
	@Email
	private String email;

	private String numeroDoDocumento;

	private String tipoCliente;

	/** Construtor. */
	public ClienteDTO() {
	}

	/** Construtor pelo domínio. */
	public ClienteDTO(Cliente cliente) {
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

	/** {@inheritDoc} */
	@Override
	public Cliente paraDominio(Cliente dominio) {

		if (Objects.isNull(dominio)) {
			return paraDominio();
		}

		dominio.setEmail(Optional.ofNullable(this.getEmail())
				.orElse(dominio.getEmail()));

		dominio.setNome(Optional.ofNullable(this.getNome())
				.orElse(dominio.getNome()));

		dominio.setNumeroDoDocumento(Optional.ofNullable(this.getNumeroDoDocumento())
				.orElse(dominio.getNumeroDoDocumento()));

		dominio.setTipoCliente(Optional.of(this.getTipoCliente())
				.orElse(dominio.getTipoCliente()));

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
