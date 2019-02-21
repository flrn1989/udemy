package stefanini.rh.selecao.service;

import javax.ejb.Remote;

import stefanini.rh.selecao.domain.Teste;

@Remote
public interface TesteServiceRemote {

	Teste salvar(Teste teste);

	Teste obterPor(Integer id);

}
