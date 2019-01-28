/**
 * 
 */
package udemy.curso.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udemy.curso.ExcecaoDeBuscaVazia;

/** Serviço para o domínio Categoria. */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;

	/** @return Todas as categorias presentes no banco. */
	public List<Categoria> listar() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Categoria referente ao ID fornecido.
	 * @throws Throwable */
	public Categoria obterPor(Integer id) throws Throwable {

		Optional<Categoria> categoria = repositorio.findById(id);

		return categoria.orElseThrow(() -> new ExcecaoDeBuscaVazia());
	}

}
