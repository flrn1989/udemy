/**
 * 
 */
package udemy.curso.controladores;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import udemy.curso.interfaces.Dominio;
import udemy.curso.servicos.ServicoDeDominio;

/** Controlador abstrato para os demais controladores de domínio. */
public abstract class ControladorDeDominio<TipoDoDominio extends Dominio, TipoDoId, RepositorioDoDominio extends JpaRepository<TipoDoDominio, TipoDoId>> {

	@Autowired
	protected ServicoDeDominio<TipoDoDominio, TipoDoId, RepositorioDoDominio> servico;

	/** @param dominio
	 * @return Domínio salvo. */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody TipoDoDominio dominio) {

		dominio = servico.salvar(dominio);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dominio.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	/** @return Listagem de todos os domínios encontrados. */
	@RequestMapping(method = RequestMethod.GET)
	public List<TipoDoDominio> listarTodos() {
		return servico.listarTodos();
	}

	/** @param id
	 * @return Domínio de ID referente. */
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<TipoDoDominio> obterPorId(@PathVariable TipoDoId id) {
		return ResponseEntity.ok(servico.obterPorId(id));
	}

	/** @param id
	 * @return NoContent
	 * @throws IllegalArgumentException caso o objeto para deleção seja nulo. */
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable TipoDoId id) throws IllegalArgumentException {
		servico.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}

}
