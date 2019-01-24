/**
 * 
 */
package udemy.curso.categoria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** Controller do domínio Categoria. */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService servico;

	/** @return Todas as Categorias. */
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {

		Categoria categoria1 = new Categoria(1L, "Militar");
		Categoria categoria2 = new Categoria(2L, "Direito");

		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(categoria1);
		categorias.add(categoria2);

		return categorias;
	}

	/** @param id
	 * @return Categoria referente ao ID fornecido.
	 * @throws Throwable */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> obterPor(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(servico.obterPor(id));
		} catch (Throwable e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}