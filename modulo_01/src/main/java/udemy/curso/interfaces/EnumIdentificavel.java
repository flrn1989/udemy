/**
 * 
 */
package udemy.curso.interfaces;

/** Contrato para obtenção de identificação de enum. */
public interface EnumIdentificavel extends Identificavel {

	/** @return id referente. */
	Integer getId();

	/** @return descrição referente. */
	String getDescricao();

}
