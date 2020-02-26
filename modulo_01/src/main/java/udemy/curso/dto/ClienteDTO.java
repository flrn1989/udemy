/**
 * 
 */
package udemy.curso.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import udemy.curso.dominios.Cliente;
import udemy.curso.dominios.Endereco;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.anotacoes.CPFOuCNPJ;

/** DTO de Cliente. */
public class ClienteDTO implements DTO<Cliente> {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório.")
	@Size(min = 8, max = 160)
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório.")
	@Email
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório.")
	@CPFOuCNPJ
	private String numeroDoDocumento;

	@NotEmpty(message = "Preenchimento obrigatório.")
	private String tipoCliente;

	@NotEmpty(message = "Preenchimento obrigatório.")
	private Set<EnderecoDTO> enderecos = new HashSet<>();

	@NotEmpty(message = "Preenchimento obrigatório.")
	private Set<String> telefones = new HashSet<>();

	/** Construtor. */
	public ClienteDTO() {
	}

	/** Construtor pelo domínio. */
	public ClienteDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.numeroDoDocumento = cliente.getNumeroDoDocumento();
		this.tipoCliente = cliente.getTipoCliente();
		this.telefones = cliente.getTelefones();

		this.enderecos = cliente.getEnderecos()
				.stream()
				.map(EnderecoDTO::new)
				.collect(Collectors.toSet());
	}

	/** {@inheritDoc} */
	@Override
	public Cliente paraDominio() {
		return new Cliente(this);
	}

	/** {@inheritDoc} */
	@Override
	public Cliente preencher(Cliente dominio) {

		if (Objects.isNull(dominio)) {
			return paraDominio();
		}

		dominio.setEmail(Optional.ofNullable(this.getEmail())
				.orElse(dominio.getEmail()));

		dominio.setNome(Optional.ofNullable(this.getNome())
				.orElse(dominio.getNome()));

		dominio.setNumeroDoDocumento(Optional.ofNullable(this.getNumeroDoDocumento())
				.orElse(dominio.getNumeroDoDocumento()));

		dominio.setTipoCliente(Optional.ofNullable(this.getTipoCliente())
				.orElse(dominio.getTipoCliente()));

		dominio.setTelefones(Optional.ofNullable(this.telefones)
				.orElse(dominio.getTelefones()));

		dominio.setEnderecos(Optional.of(this.enderecos)
				.map(e -> e.stream()
						.map(Endereco::new)
						.collect(Collectors.toSet()))
				.orElse(dominio.getEnderecos()));

		dominio.getEnderecos().stream()
				.forEach(e -> e.setCliente(dominio));

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

	/** @return the enderecos */
	public Set<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	/** @param enderecos the enderecos to set */
	public void setEnderecos(Set<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	/** @return the telefones */
	public Set<String> getTelefones() {
		return telefones;
	}

	/** @param telefones the telefones to set */
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

}
