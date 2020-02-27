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

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import udemy.curso.dominios.enums.TipoCliente;
import udemy.curso.dto.ClienteDTO;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;
import udemy.curso.util.ExtratoraDeEnum;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = { "email", "idDoTipoCliente", "numeroDoDocumento" })
@Data
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

	public Cliente(String nome, String email, String numeroDoDocumento, TipoCliente tipo) {
		this.nome = nome;
		this.email = email;
		this.numeroDoDocumento = numeroDoDocumento;
		setTipoCliente(tipo);
	}

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
				.map(Endereco::new)
				.collect(Collectors.toSet());
		this.enderecos.stream().forEach(e -> e.setCliente(this));
	}

	@Override
	public DTO<Cliente> paraDTO() {
		return new ClienteDTO(this);
	}

	public String getTipoCliente() {
		return Optional.ofNullable(idDoTipoCliente)
				.map(t -> ExtratoraDeEnum
						.extrairDe(TipoCliente.values())
						.valorCom(t)
						.getDescricao())
				.orElse(StringUtils.EMPTY);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.idDoTipoCliente = Optional.ofNullable(tipoCliente)
				.map(TipoCliente::getId)
				.orElseThrow(() -> new IllegalArgumentException("O tipo do cliente não pode ser nulo."));
	}

	public void setTipoCliente(String descricaoDoTipoCliente) {
		TipoCliente tipoCliente = (TipoCliente) ExtratoraDeEnum
				.extrairDe(TipoCliente.values())
				.valorCom(descricaoDoTipoCliente);
		this.idDoTipoCliente = tipoCliente.getId();
	}

}
