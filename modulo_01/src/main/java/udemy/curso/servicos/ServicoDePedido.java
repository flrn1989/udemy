/**
 * 
 */
package udemy.curso.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udemy.curso.dominios.Pedido;
import udemy.curso.excecoes.ExcecaoDeBuscaVazia;
import udemy.curso.repositorios.RepositorioDePedido;

/** Serviço de Pedido. */
@Service
public class ServicoDePedido {

	@Autowired
	private RepositorioDePedido repositorio;

	/** @param id
	 * @return Pedido de id referente. */
	public Pedido de(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new ExcecaoDeBuscaVazia());
	}

}
