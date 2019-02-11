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

import udemy.curso.excecoes.ExcecaoDeBuscaVazia;
import udemy.curso.interfaces.Dominio;

/** Controlador abstrato para os demais controladores de domínio. */
public abstract class ControladorDeDominio<TipoDoDominio extends Dominio> {

	@Autowired
	private JpaRepository<TipoDoDominio, Integer> repositorio;

	/** @param dominio
	 * @return Domínio salvo. */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody TipoDoDominio dominio) {

		dominio = repositorio.save(dominio);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dominio.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> salvar(@RequestBody TipoDoDominio dominio,
			@PathVariable Integer id) {

		dominio.setId(id);
		repositorio.save(dominio);
		return ResponseEntity.noContent().build();
	}

	/** @return Listagem de todos os domínios encontrados. */
	@RequestMapping(method = RequestMethod.GET)
	public List<TipoDoDominio> listarTodos() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Domínio de ID referente. */
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<TipoDoDominio> obterPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(
				repositorio
						.findById(id)
						.orElseThrow(() -> new ExcecaoDeBuscaVazia()));
	}

	/** @param id
	 * @return NoContent
	 * @throws IllegalArgumentException caso o objeto para deleção seja nulo. */
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) throws IllegalArgumentException {
		repositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
