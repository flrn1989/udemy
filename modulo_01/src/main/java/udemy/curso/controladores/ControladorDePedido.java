/**
 * 
 */
package udemy.curso.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Pedido;
import udemy.curso.servicos.ServicoDePedido;

/** Controlador do domínio Pedido. */
@RestController
@RequestMapping(value = "/pedidos")
public class ControladorDePedido {

	@Autowired
	private ServicoDePedido servico;

	/** @param id
	 * @return Pedido de id referente. */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> de(@PathVariable Integer id) {
		return ResponseEntity.ok(servico.de(id));
	}

}
