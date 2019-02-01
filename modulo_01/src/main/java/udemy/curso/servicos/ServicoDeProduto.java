/**
 * 
 */
package udemy.curso.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udemy.curso.dominios.Produto;
import udemy.curso.excecoes.ExcecaoDeBuscaVazia;
import udemy.curso.repositorios.RepositorioDeProduto;

/** Serviço de Produto. */
@Service
public class ServicoDeProduto {

	@Autowired
	private RepositorioDeProduto repositorio;

	/** @return Todos os produtos presentes no banco. */
	public List<Produto> listar() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Produto referente ao ID fornecido. */
	public Produto obterPor(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new ExcecaoDeBuscaVazia());
	}

}
