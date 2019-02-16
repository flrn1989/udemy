/**
 * 
 */
package udemy.curso.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Cliente;
import udemy.curso.dto.ClienteDTO;
import udemy.curso.repositorios.RepositorioDeEndereco;

/** Controlador do domínio Cliente. */
@RestController
@RequestMapping(value = "/clientes")
public class ControladorDeCliente extends ControladorDeDominio<Cliente, ClienteDTO> {

	@Autowired
	private RepositorioDeEndereco repositorioDeEndereco;

	/** {@inheritDoc} */
	@Override
	protected ControladorDeDominio<Cliente, ClienteDTO> prepararCriacao(Cliente dominio) {

		dominio.getEnderecos().stream()
				.forEach(e -> e.setCliente(dominio));

		repositorioDeEndereco.saveAll(dominio.getEnderecos());

		return super.prepararCriacao(dominio);
	}

}