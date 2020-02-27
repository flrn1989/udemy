package udemy.curso.dto;

import java.util.Objects;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import udemy.curso.dominios.Cidade;
import udemy.curso.interfaces.DTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO implements DTO<Cidade> {

	private static final long serialVersionUID = 1L;

	private String nome;
	private EstadoDTO estado;

	@Override
	public Cidade preencher(Cidade dominio) throws ReflectiveOperationException {
		if (Objects.isNull(dominio)) {
			dominio = new Cidade();
		}
		dominio.setNome(Optional
				.ofNullable(nome)
				.orElse(dominio.getNome()));
		dominio.setEstado(Optional
				.ofNullable(estado.paraDominio())
				.orElse(dominio.getEstado()));
		return dominio;
	}

}
