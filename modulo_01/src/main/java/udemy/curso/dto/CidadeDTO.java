package udemy.curso.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import udemy.curso.dominios.Cidade;
import udemy.curso.dominios.Estado;
import udemy.curso.interfaces.DTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO implements DTO<Cidade> {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Estado estado;

	@Override
	public Cidade paraDominio() {
		return preencher(new Cidade());
	}

	@Override
	public Cidade preencher(Cidade dominio) {
		if (Objects.isNull(dominio)) {
			return null;
		}
		dominio.setNome(nome);
		dominio.setEstado(estado);
		return dominio;
	}

}
