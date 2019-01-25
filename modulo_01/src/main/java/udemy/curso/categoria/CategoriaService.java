/**
 * 
 */
package udemy.curso.categoria;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Serviço para o domínio Categoria. */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;

	/** Popula a base de dados com massa para teste.
	 * 
	 * @return o próprio serviço para chainning. */
	public CategoriaService popularBaseDeDadosDeTeste() {

		Categoria categoria1 = new Categoria(1L, "Militar");
		Categoria categoria2 = new Categoria(2L, "Policial");
		Categoria categoria3 = new Categoria(3L, "Direito");

		List<Categoria> categorias = Arrays.asList(
				categoria1,
				categoria2,
				categoria3);

		repositorio.saveAll(categorias);

		return this;
	}

	/** @return Todas as categorias presentes no banco. */
	public List<Categoria> listar() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Categoria referente ao ID fornecido.
	 * @throws Throwable */
	public Categoria obterPor(Long id) throws Throwable {

		Optional<Categoria> categoria = repositorio.findById(id);

		return categoria.orElseThrow(() -> excecaoDeBuscaVazia());
	}

	private Throwable excecaoDeBuscaVazia() {
		return new Exception("A busca realizada não obteve resultado.");
	}

}
