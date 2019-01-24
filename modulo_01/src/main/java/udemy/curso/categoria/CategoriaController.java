/**
 * 
 */
package udemy.curso.categoria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** CategoriaResource */
@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {

		Categoria categoria1 = new Categoria(1L, "Militar");
		Categoria categoria2 = new Categoria(2L, "Direito");

		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(categoria1);
		categorias.add(categoria2);

		return categorias;
	}

}