/**
 * 
 */
package udemy.curso.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udemy.curso.dominios.Categoria;
import udemy.curso.excecoes.ExcecaoDeBuscaVazia;
import udemy.curso.repositorios.RepositorioDeCategoria;

/** Serviço para o domínio Categoria. */
@Service
public class ServicoDeCategoria {

	@Autowired
	private RepositorioDeCategoria repositorio;

	/** @return Todas as categorias presentes no banco. */
	public List<Categoria> listar() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Categoria referente ao ID fornecido. */
	public Categoria obterPor(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new ExcecaoDeBuscaVazia());
	}

}
