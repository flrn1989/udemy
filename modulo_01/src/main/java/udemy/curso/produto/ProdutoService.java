/**
 * 
 */
package udemy.curso.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udemy.curso.ExcecaoDeBuscaVazia;

/** Serviço de Produto. */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio;

	/** @return Todos os produtos presentes no banco. */
	public List<Produto> listar() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Produto referente ao ID fornecido.
	 * @throws Throwable */
	public Produto obterPor(Integer id) throws Throwable {

		Optional<Produto> produto = repositorio.findById(id);

		return produto.orElseThrow(() -> new ExcecaoDeBuscaVazia());
	}

}
