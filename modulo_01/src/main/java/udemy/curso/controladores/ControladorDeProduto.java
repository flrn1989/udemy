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

import udemy.curso.dominios.Produto;
import udemy.curso.servicos.ServicoDeProduto;

/** Controlador do domínio Produto. */
@RestController
@RequestMapping(value = "/produtos")
public class ControladorDeProduto {

	@Autowired
	private ServicoDeProduto servico;

	/** @return Todos os produtos. */
	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> listar() {
		return servico.listar();
	}

	/** @param id
	 * @return Produto referente ao ID fornecido. */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> obterPor(@PathVariable Integer id) {
		return ResponseEntity.ok(servico.obterPor(id));
	}

}