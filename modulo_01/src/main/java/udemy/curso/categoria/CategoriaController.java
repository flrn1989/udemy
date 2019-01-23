/**
 * 
 */
package udemy.curso.categoria;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** CategoriaResource */
@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "Está funcionando!";
	}

}