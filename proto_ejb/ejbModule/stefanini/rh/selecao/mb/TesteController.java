/**
 * 
 */
package stefanini.rh.selecao.mb;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import stefanini.rh.selecao.domain.Teste;
import stefanini.rh.selecao.service.impl.TesteService;

/**
 * 
 */
@ManagedBean
public class TesteController {

	@EJB
	private TesteService servico;

	private Teste testeSalvo;

	@PostConstruct
	public void initialize() {

		Teste testeNovo = new Teste();
		testeNovo.setNome("Teste novo");
		servico.salvar(testeNovo);

		testeSalvo = servico.obterPor(1);
		System.out.println(testeSalvo.getNome());
	}

	public Teste salvar() {
		servico.salvar(testeSalvo);
		System.out.println(testeSalvo.getNome());
		return testeSalvo;
	}

	/** @return the testeSalvo */
	public Teste getTesteSalvo() {
		return testeSalvo;
	}

	/** @param testeSalvo the testeSalvo to set */
	public void setTesteSalvo(Teste testeSalvo) {
		this.testeSalvo = testeSalvo;
	}

}
