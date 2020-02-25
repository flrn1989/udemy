package udemy.curso.dominios;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import udemy.curso.dto.CategoriaDTO;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;

@Entity
@EqualsAndHashCode(of = "nome")
@Data
@NoArgsConstructor
public class Categoria implements Dominio {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@JsonIgnore
	@ManyToMany(mappedBy = "categorias")
	private Set<Produto> produtos = new HashSet<>();

	public Categoria(String nome) {
		this.nome = nome;
	}

	public Categoria(CategoriaDTO dto) {
		this.nome = dto.getNome();
	}

	@Override
	public DTO<Categoria> paraDTO() {
		return new CategoriaDTO(this);
	}

}
