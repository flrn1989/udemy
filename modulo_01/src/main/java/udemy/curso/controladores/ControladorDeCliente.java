/**
 * 
 */
package udemy.curso.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Cliente;
import udemy.curso.servicos.ServicoDeCliente;

/** Controlador do domínio Cliente. */
@RestController
@RequestMapping(value = "/clientes")
public class ControladorDeCliente {

	@Autowired
	private ServicoDeCliente servico;

	/** @return Todos os clientes. */
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> listar() {
		return servico.listar();
	}

	/** @param id
	 * @return Cliente referente ao ID fornecido. */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> obterPor(@PathVariable Integer id) {
		return ResponseEntity.ok(servico.obterPor(id));
	}

}