package udemy.curso.dominios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import udemy.curso.dto.EstadoDTO;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "nome")
public class Estado implements Dominio {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();

	public Estado(String nome) {
		this.nome = nome;
	}

	@Override
	public DTO<Estado> paraDTO() {
		return new EstadoDTO(this.nome);
	}

}
