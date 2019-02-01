/**
 * 
 */
package udemy.curso.dominios.enums;

/** Enumerador para TipoCliente. */
public enum TipoCliente {

	PF(1, "Pessoa Física"),
	PJ(2, "Pessoa Jurídica");

	private Integer id;
	private String descricao;

	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	/** @param id
	 * @return O TipoCliente referente ao id fornecido.
	 * @throws IllegalArgumentException caso o id seja <code>null</code> ou inexista
	 *                                  no rol do enum. */
	public static TipoCliente de(Integer id) throws IllegalArgumentException {

		if (id == null) {
			throw new IllegalArgumentException("O ID do TipoCliente não pode ser nulo.");
		}

		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if (tipoCliente.getId().equals(id)) {
				return tipoCliente;
			}
		}

		throw new IllegalArgumentException("O ID do TipoCliente é inexistente.");
	}

	/** @return the id */
	public Integer getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return the descricao */
	public String getDescricao() {
		return descricao;
	}

	/** @param descricao the descricao to set */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
