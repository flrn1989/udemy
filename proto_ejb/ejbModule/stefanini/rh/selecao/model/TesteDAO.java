/**
 * 
 */
package stefanini.rh.selecao.model;

import java.io.Serializable;

/**
 * 
 */
public interface TesteDAO<T extends Serializable> {

	T salvar(T teste);

	T obterPor(Integer id);

}
