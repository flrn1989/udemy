/**
 * 
 */
package stefanini.rh.selecao.model.impl;

import javax.persistence.EntityManager;

import stefanini.rh.selecao.domain.Teste;
import stefanini.rh.selecao.model.TesteDAO;

/**
 * 
 */
public class TesteDAOImpl implements TesteDAO<Teste> {

	private EntityManager em;

	public TesteDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Teste salvar(Teste teste) {
		em.persist(teste);
		return teste;
	}

	@Override
	public Teste obterPor(Integer id) {
		return em.find(Teste.class, id);
	}

}
