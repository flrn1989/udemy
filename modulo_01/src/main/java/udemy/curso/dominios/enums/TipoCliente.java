/**
 * 
 */
package udemy.curso.dominios.enums;

import udemy.curso.interfaces.EnumIdentificavel;

/** Enumerador para TipoCliente. */
public enum TipoCliente implements EnumIdentificavel {

	PF(1, "Pessoa Física"),
	PJ(2, "Pessoa Jurídica");

	private Integer id;

	private String descricao;

	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	/** {@inheritDoc} */
	public Integer getId() {
		return id;
	}

	/** {@inheritDoc} */
	public String getDescricao() {
		return descricao;
	}

}
