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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import udemy.curso.excecoes.rest.ExcecaoDeBuscaVazia;
import udemy.curso.excecoes.rest.ExcecaoDeIntegridadeDeDados;
import udemy.curso.interfaces.DTO;
import udemy.curso.interfaces.Dominio;

public abstract class ControladorDeDominio<D extends Dominio, O extends DTO<D>> {

	@Autowired
	protected JpaRepository<D, Integer> repositorio;

	@PostMapping
	@Transactional
	public ResponseEntity<Void> salvar(@Valid @RequestBody O dto) throws ReflectiveOperationException {
		D dominio = dto.paraDominio();
		preInsercao(dominio);
		dominio.setId(null);
		dominio = repositorio.save(dominio);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dominio.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> salvar(@Valid @RequestBody O dto, @PathVariable Integer id)
			throws ReflectiveOperationException {
		D dominio = dto.preencher(this.obterPorId(id).getBody());
		preAtualizacao(dominio);
		repositorio.save(dominio);
		return ResponseEntity.noContent().build();
	}

	@SuppressWarnings("unchecked")
	@GetMapping
	public List<O> listarTodos() {
		return (List<O>) repositorio.findAll()
				.stream()
				.map(D::paraDTO)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/paginacao")
	public Page<O> listarPorPagina(
			@RequestParam(value = "pag", defaultValue = "0") Integer pagina,
			@RequestParam(value = "qtd", defaultValue = "3") Integer quantidadePorPagina,
			@RequestParam(value = "dir", defaultValue = "asc") String ordenacao,
			@RequestParam(value = "prp", defaultValue = "id") String... propriedadesOrdenadoras) {
		PageRequest paginavel = PageRequest.of(
				pagina,
				quantidadePorPagina,
				Direction.fromOptionalString(ordenacao).orElse(Direction.ASC),
				propriedadesOrdenadoras);
		return (Page<O>) repositorio.findAll(paginavel).map(D::paraDTO);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<D> obterPorId(@PathVariable Integer id) {
		return repositorio.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(ExcecaoDeBuscaVazia::new);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		ResponseEntity<D> dominio = this.obterPorId(id);
		preDelecao(dominio.getBody());
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ExcecaoDeIntegridadeDeDados(e);
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Método a ser sobrescrito pela especialização do controlador no caso de
	 * necessidade.
	 * 
	 * @param dominio
	 */
	protected void preInsercao(D dominio) {}

	/*
	 * Método a ser sobrescrito pela especialização do controlador no caso de
	 * necessidade.
	 * 
	 * @param dominio
	 */
	protected void preAtualizacao(D dominio) {}

	/*
	 * Método a ser sobrescrito pela especialização do controlador no caso de
	 * necessidade.
	 * 
	 * @param dominio
	 */
	protected void preDelecao(D dominio) {}

}
