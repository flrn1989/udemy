/**
 * 
 */
package udemy.curso.interfaces;

import java.io.Serializable;

/** Interface para os DTOs. */
public interface DTO<TipoDoDominio extends Dominio> extends Identificavel, Serializable {

	/** @return Instância do domínio com os dados do DTO. */
	TipoDoDominio paraDominio();

}
