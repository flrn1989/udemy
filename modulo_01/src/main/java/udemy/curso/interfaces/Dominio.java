/**
 * 
 */
package udemy.curso.interfaces;

import java.io.Serializable;

/** Interface para os domínios. */
public interface Dominio extends Identificavel, Serializable {

	/** Configura o id do domínio.
	 * 
	 * @param id */
	void setId(Integer id);

	/** @return instância de DTO do domínio. */
	DTO<? extends Dominio> paraDTO();

}
