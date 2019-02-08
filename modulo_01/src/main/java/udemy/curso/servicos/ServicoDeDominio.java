/**
 * 
 */
package udemy.curso.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import udemy.curso.excecoes.ExcecaoDeBuscaVazia;

/** Classe para os demais serviços de domínio. */
public abstract class ServicoDeDominio<TipoDoDominio, TipoDoId, RepositorioDoDominio extends JpaRepository<TipoDoDominio, TipoDoId>> {

	@Autowired
	protected RepositorioDoDominio repositorio;

	/** @param dominio
	 * @return Domínio salvo. */
	public TipoDoDominio salvar(TipoDoDominio dominio) {
		return repositorio.save(dominio);
	}

	/** @return Listagem de todos os domínios encontrados. */
	public List<TipoDoDominio> listarTodos() {
		return repositorio.findAll();
	}

	/** @param id
	 * @return Domínio de ID referente. */
	public TipoDoDominio obterPorId(TipoDoId id) {
		return repositorio.findById(id).orElseThrow(() -> new ExcecaoDeBuscaVazia());
	}

	/** @param id
	 * @return O próprio serviço para encadeamento.
	 * @throws IllegalArgumentException caso o objeto para deleção seja nulo. */
	public ServicoDeDominio<TipoDoDominio, TipoDoId, RepositorioDoDominio> deletarPorId(
			TipoDoId id) throws IllegalArgumentException {

		repositorio.deleteById(id);
		return this;
	}

}
