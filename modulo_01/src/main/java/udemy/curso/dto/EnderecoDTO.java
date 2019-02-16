/**
 * 
 */
package udemy.curso.dto;

import java.util.Objects;
import java.util.Optional;

import udemy.curso.dominios.Cidade;
import udemy.curso.dominios.Endereco;
import udemy.curso.interfaces.DTO;

/** DTO para Endereço. */
public class EnderecoDTO implements DTO<Endereco> {

	private static final long serialVersionUID = 1L;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private Integer idCidade;

	/** Construtor padrão. */
	public EnderecoDTO() {
	}

	/** @param endereco */
	public EnderecoDTO(Endereco endereco) {

		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();

		this.idCidade = Optional.ofNullable(endereco.getCidade())
				.map(c -> c.getId())
				.orElse(null);
	}

	/** {@inheritDoc} */
	@Override
	public Endereco paraDominio() {
		return new Endereco(this);
	}

	/** {@inheritDoc} */
	@Override
	public Endereco paraDominio(Endereco dominio) {

		if (Objects.isNull(dominio)) {
			return paraDominio();
		}

		dominio.setLogradouro(Optional.ofNullable(this.getLogradouro())
				.orElse(dominio.getLogradouro()));

		dominio.setNumero(Optional.ofNullable(this.getNumero())
				.orElse(dominio.getNumero()));

		dominio.setComplemento(Optional.ofNullable(this.getComplemento())
				.orElse(dominio.getComplemento()));

		dominio.setBairro(Optional.ofNullable(this.getBairro())
				.orElse(dominio.getBairro()));

		dominio.setCep(Optional.ofNullable(this.getCep())
				.orElse(dominio.getCep()));

		dominio.setCidade(Optional.ofNullable(this.idCidade)
				.map(c -> new Cidade(this.idCidade))
				.orElse(dominio.getCidade()));

		return dominio;
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

	/** @return the idCidade */
	public Integer getIdCidade() {
		return idCidade;
	}

	/** @param idCidade the idCidade to set */
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

}
