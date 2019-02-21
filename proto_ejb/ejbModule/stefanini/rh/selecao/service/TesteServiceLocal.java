package stefanini.rh.selecao.service;

import javax.ejb.Local;

import stefanini.rh.selecao.domain.Teste;

@Local
public interface TesteServiceLocal {

	Teste salvar(Teste teste);

	Teste obterPor(Integer id);

}
