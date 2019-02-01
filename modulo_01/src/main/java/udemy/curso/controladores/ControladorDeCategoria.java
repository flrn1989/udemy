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

import udemy.curso.dominios.Categoria;
import udemy.curso.servicos.ServicoDeCategoria;

/** Controller do domínio Categoria. */
@RestController
@RequestMapping(value = "/categorias")
public class ControladorDeCategoria {

	@Autowired
	private ServicoDeCategoria servico;

	/** @return Todas as Categorias. */
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		return servico.listar();
	}

	/** @param id
	 * @return Categoria referente ao ID fornecido. */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> obterPor(@PathVariable Integer id) {
		return ResponseEntity.ok(servico.obterPor(id));
	}

}