package udemy.curso.dominios;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import udemy.curso.dto.CidadeDTO;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = { "nome", "estado" })
public class Cidade implements Dominio {

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

	public Cidade(String nome, Estado estado) {
		this.nome = nome;
		this.estado = estado;
		estado.getCidades().add(this);
	}

	public Cidade(Integer id) {
		this.id = id;
	}

	@Override
	public DTO<Cidade> paraDTO() {
		return new CidadeDTO(nome, estado);
	}

}
