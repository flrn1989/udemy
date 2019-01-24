/**
 * 
 */
package udemy.curso.categoria;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Serviço para o domínio Categoria. */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;

	/** @param id
	 * @return Categoria referente ao ID fornecido.
	 * @throws Throwable */
	public Categoria obterPor(Long id) throws Throwable {

		Optional<Categoria> categoria = repositorio.findById(id);

		return categoria.orElseThrow(suprirExcecaoDeBuscaVazia());
	}

	private Supplier<Throwable> suprirExcecaoDeBuscaVazia() {
		return new Supplier<Throwable>() {
			@Override
			public Throwable get() {
				return new Exception("A busca realizada não obteve resultado.");
			}
		};
	}

}
