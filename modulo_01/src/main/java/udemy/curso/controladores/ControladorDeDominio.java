/**
 * 
 */
package udemy.curso.controladores;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import udemy.curso.excecoes.rest.ExcecaoDeBuscaVazia;
import udemy.curso.excecoes.rest.ExcecaoDeIntegridadeDeDados;
import udemy.curso.interfaces.Dominio;

/** Controlador abstrato para os demais controladores de domínio. */
public abstract class ControladorDeDominio<TipoDoDominio extends Dominio> {

	@Autowired
	private JpaRepository<TipoDoDominio, Integer> repositorio;

	/** @param dominio
	 * @return Domínio salvo. */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody TipoDoDominio dominio) {

		dominio.setId(null);
		dominio = repositorio.save(dominio);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dominio.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	/** @param dominio
	 * @param id
	 * @return Response sem conteúdo. */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> salvar(@RequestBody TipoDoDominio dominio,
			@PathVariable Integer id) {

		this.obterPorId(id);
		dominio.setId(id);
		repositorio.save(dominio);
		return ResponseEntity.noContent().build();
	}

	/** @return Listagem dos DTOs de todos os domínios encontrados. */
	@RequestMapping(method = RequestMethod.GET)
	public List<?> listarTodos() {
		return repositorio.findAll()
				.stream()
				.map(obj -> obj.paraDTO())
				.collect(Collectors.toList());
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
	 * @throws ExcecaoDeIntegridadeDeDados quando o objeto não pôde ser manipulado
	 *                                     por possuir vínculos no banco de
	 *                                     dados. */
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id)
			throws Throwable {

		this.obterPorId(id);

		try {
			repositorio.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new ExcecaoDeIntegridadeDeDados(e);
		}

		return ResponseEntity.noContent().build();
	}

}
