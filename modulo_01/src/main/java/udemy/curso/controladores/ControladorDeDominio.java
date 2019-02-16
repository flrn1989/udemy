/**
 * 
 */
package udemy.curso.controladores;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import udemy.curso.excecoes.rest.ExcecaoDeBuscaVazia;
import udemy.curso.excecoes.rest.ExcecaoDeIntegridadeDeDados;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;

/** Controlador abstrato para os demais controladores de domínio. */
public abstract class ControladorDeDominio<TipoDoDominio extends Dominio, TipoDoDTO extends DTO<TipoDoDominio>> {

	@Autowired
	protected JpaRepository<TipoDoDominio, Integer> repositorio;

	/** @param dto
	 * @return Response sem conteúdo. */
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<Void> salvar(@Valid @RequestBody TipoDoDTO dto) {

		TipoDoDominio dominio = (TipoDoDominio) dto.paraDominio();

		prepararCriacao(dominio);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dominio.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	/** @param dominio
	 * @return Método a ser sobrescrito caso haja alguma operação para realizar
	 *         antes da criação. */
	protected ControladorDeDominio<TipoDoDominio, TipoDoDTO> prepararCriacao(
			TipoDoDominio dominio) {

		dominio.setId(null);

		dominio = repositorio.save(dominio);

		return this;
	}

	/** @param dto
	 * @param id
	 * @return Response sem conteúdo. */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> salvar(
			@Valid @RequestBody TipoDoDTO dto,
			@PathVariable Integer id) {

		TipoDoDominio dominio = (TipoDoDominio) dto.paraDominio(
				this.obterPorId(id)
						.getBody());

		prepararAtualizacao(dominio, id);

		return ResponseEntity.noContent().build();
	}

	/** @param dominio
	 * @param id
	 * @return Método a ser sobrescrito caso haja alguma operação para realizar
	 *         antes da atualização. */
	protected ControladorDeDominio<TipoDoDominio, TipoDoDTO> prepararAtualizacao(
			TipoDoDominio dominio,
			Integer id) {

		repositorio.save(dominio);

		return this;
	}

	/** @return Listagem dos DTOs de todos os domínios encontrados. */
	@RequestMapping(method = RequestMethod.GET)
	public List<?> listarTodos() {
		return repositorio.findAll()
				.stream()
				.map(obj -> obj.paraDTO())
				.collect(Collectors.toList());
	}

	/** @param pagina
	 * @param quantidadePorPagina
	 * @param ordenacao
	 * @param propriedadesOrdenadoras
	 * @return Listagem dos DTOs dos domínios paginados. */
	@RequestMapping(method = RequestMethod.GET, value = "/paginacao")
	public Page<?> listarPorPagina(
			@RequestParam(value = "pag",
					defaultValue = "0") Integer pagina,

			@RequestParam(value = "qtd",
					defaultValue = "3") Integer quantidadePorPagina,

			@RequestParam(value = "dir",
					defaultValue = "asc") String ordenacao,

			@RequestParam(value = "prp",
					defaultValue = "id") String... propriedadesOrdenadoras) {

		PageRequest paginavel = PageRequest.of(
				pagina,
				quantidadePorPagina,
				Direction.fromOptionalString(ordenacao).orElse(Direction.ASC),
				propriedadesOrdenadoras);

		return repositorio.findAll(paginavel).map(obj -> obj.paraDTO());
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
	@Transactional
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id)
			throws Throwable {

		this.obterPorId(id);

		try {
			prepararDelecao(id);

		} catch (DataIntegrityViolationException e) {
			throw new ExcecaoDeIntegridadeDeDados(e);
		}

		return ResponseEntity.noContent().build();
	}

	/** @param id
	 * @return Método a ser sobrescrito caso haja alguma operação para realizar
	 *         antes da deleção. */
	protected ControladorDeDominio<TipoDoDominio, TipoDoDTO> prepararDelecao(Integer id) {

		repositorio.deleteById(id);

		return this;
	}

}
