/**
 * 
 */
package udemy.curso.recursos;

import udemy.curso.interfaces.Identificavel;

/** Classe utilitária para extrair valores de enums que implementam a interface
 * EnumIdentificavel. */
public final class ExtratoraDeEnumIdentificavel {

	private Identificavel[] valoresEnumerados;

	/** Construtor.
	 * 
	 * @param valoresDoEnumIdentificavel valores do enum a ser percorrido.
	 * @throws IllegalArgumentException caso os valores sejam <code>null</code>.
	 * @throws ClassCastException       caso os valores passados não implementem
	 *                                  Identificavel. */
	@SuppressWarnings("rawtypes")
	public ExtratoraDeEnumIdentificavel(Enum[] valoresDoEnumIdentificavel)
			throws IllegalArgumentException, ClassCastException {

		if (valoresDoEnumIdentificavel == null) {
			throw new IllegalArgumentException("O Enum não pode ser nulo.");
		}

		try {
			this.valoresEnumerados = (Identificavel[]) valoresDoEnumIdentificavel;

		} catch (ClassCastException e) {
			throw new ClassCastException(
					"O Enum deve implementar Identificavel.\n"
							.concat(e.getMessage()));
		}
	}

	/** @param id
	 * @return Enum com o id correspondente.
	 * @throws IllegalArgumentException caso o id seja <code>null</code> ou inexista
	 *                                  no rol do enum. */
	public final Identificavel de(Integer id) throws IllegalArgumentException {

		if (id == null) {
			throw new IllegalArgumentException("O ID do Enum não pode ser nulo.");
		}

		for (Identificavel valorEnumerado : valoresEnumerados) {
			if (id.equals(valorEnumerado.getId())) {
				return valorEnumerado;
			}
		}

		throw new IllegalArgumentException("O ID do Enum é inexistente.");
	}

}
