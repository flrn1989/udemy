/**
 * 
 */
package udemy.curso.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** Controller do domínio Produto. */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService servico;

	/** @return Todos os produtos. */
	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> listar() {
		return servico.listar();
	}

	/** @param id
	 * @return Produto referente ao ID fornecido.
	 * @throws Throwable */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> obterPor(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(servico.obterPor(id));
		} catch (Throwable e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}