/**
 * 
 */
package udemy.curso.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udemy.curso.dominios.Cliente;
import udemy.curso.excecoes.ExcecaoDeBuscaVazia;
import udemy.curso.repositorios.RepositorioDeCliente;

/** Serviço de Cliente. */
@Service
public class ServicoDeCliente {

	@Autowired
	private RepositorioDeCliente repositorio;

	/** @return Todos os clientes presentes no banco. */
	public List<Cliente> listar() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Cliente referente ao ID fornecido. */
	public Cliente obterPor(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new ExcecaoDeBuscaVazia());
	}

}
