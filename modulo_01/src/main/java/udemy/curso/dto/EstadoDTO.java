package udemy.curso.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import udemy.curso.dominios.Estado;
import udemy.curso.interfaces.DTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO implements DTO<Estado> {

	private static final long serialVersionUID = 1L;

	private String nome;

	@Override
	public Estado preencher(Estado dominio) {
		if (Objects.isNull(dominio)) {
			return null;
		}
		this.nome = dominio.getNome();
		return dominio;
	}

}
