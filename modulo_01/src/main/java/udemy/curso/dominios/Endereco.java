package udemy.curso.dominios;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import udemy.curso.dto.EnderecoDTO;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "id", "cliente" })
@Data
public class Endereco implements Dominio {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "CIDADE_ID")
	private Cidade cidade;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Endereco(EnderecoDTO endereco, Cidade cidade) {
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.cidade = Optional.ofNullable(cidade)
				.orElseGet(() -> new Cidade(endereco.getIdCidade()));
	}

	public Endereco(EnderecoDTO endereco) {
		this(endereco, null);
	}

	@Override
	public DTO<Endereco> paraDTO() {
		return new EnderecoDTO(this);
	}

}
