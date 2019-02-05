/**
 * 
 */
package udemy.curso.dominios.enums;

import udemy.curso.interfaces.EnumIdentificavel;

/** Enumerador para estados de pagamentos. */
public enum EstadoDePagamento implements EnumIdentificavel {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private Integer id;

	private String descricao;

	/** @param id
	 * @param descricao */
	private EstadoDePagamento(Integer id, String descricao) {
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
