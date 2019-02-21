package stefanini.rh.selecao.service.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import stefanini.rh.selecao.domain.Teste;
import stefanini.rh.selecao.model.TesteDAO;
import stefanini.rh.selecao.model.impl.TesteDAOImpl;
import stefanini.rh.selecao.service.TesteServiceLocal;
import stefanini.rh.selecao.service.TesteServiceRemote;

/** Session Bean implementation class TesteService */
@Stateless
public class TesteService implements TesteServiceRemote, TesteServiceLocal {

	@PersistenceContext(unitName = "pu_base")
	private EntityManager em;

	private TesteDAO<Teste> dao;

	@PostConstruct
	public void initialize() {
		this.dao = new TesteDAOImpl(this.em);
	}

	public Teste salvar(Teste teste) {
		return dao.salvar(teste);
	}

	public Teste obterPor(Integer id) {
		return dao.obterPor(id);
	}

}
