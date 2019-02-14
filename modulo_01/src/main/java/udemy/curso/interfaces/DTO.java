/**
 * 
 */
package udemy.curso.interfaces;

import java.io.Serializable;

/** Interface para os DTOs. */
public interface DTO<TipoDoDominio extends Dominio> extends Serializable {

	/** @return Instância do domínio com os dados do DTO. */
	TipoDoDominio paraDominio();

	/** @return Atualiza o domínio com os dados do DTO. */
	TipoDoDominio paraDominio(TipoDoDominio dominio);

}
