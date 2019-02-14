/**
 * 
 */
package udemy.curso.util;

import udemy.curso.interfaces.EnumIdentificavel;

/** Classe utilitária para extrair valores de enums que implementam a interface
 * EnumIdentificavel. */
@SuppressWarnings("rawtypes")
public final class ExtratoraDeEnum {

	private EnumIdentificavel[] valoresEnumerados;

	/** Construtor.
	 * 
	 * @param valoresDoEnum valores do enum a ser percorrido.
	 * @throws IllegalArgumentException caso os valores sejam <code>null</code>.
	 * @throws ClassCastException       caso os valores passados não implementem
	 *                                  EnumIdentificavel. */
	private ExtratoraDeEnum(Enum[] valoresDoEnum)
			throws IllegalArgumentException, ClassCastException {

		if (valoresDoEnum == null) {
			throw new IllegalArgumentException("O Enum não pode ser nulo.");
		}

		try {
			this.valoresEnumerados = (EnumIdentificavel[]) valoresDoEnum;

		} catch (ClassCastException e) {
			throw new ClassCastException(
					"O Enum deve implementar EnumIdentificavel.\n"
							.concat(e.getMessage()));
		}
	}

	/** @param valoresDoEnum
	 * @return instância fabricada. */
	public static ExtratoraDeEnum extrairDe(Enum[] valoresDoEnum) {
		return new ExtratoraDeEnum(valoresDoEnum);
	}

	/** @param id
	 * @return Enum com o id correspondente.
	 * @throws IllegalArgumentException caso o id seja <code>null</code> ou inexista
	 *                                  no rol do enum. */
	public EnumIdentificavel valorCom(Integer id) throws IllegalArgumentException {

		if (id == null) {
			throw new IllegalArgumentException("O ID do Enum não pode ser nulo.");
		}

		for (EnumIdentificavel valorEnumerado : valoresEnumerados) {
			if (id.equals(valorEnumerado.getId())) {
				return valorEnumerado;
			}
		}

		throw new IllegalArgumentException("O ID do Enum é inexistente.");
	}

	/** @param descricao
	 * @return Enum com a descrição correspondente.
	 * @throws IllegalArgumentException caso a descrição seja <code>null</code> ou
	 *                                  inexista no rol do enum. */
	public EnumIdentificavel valorCom(String descricao) throws IllegalArgumentException {

		if (descricao == null) {
			throw new IllegalArgumentException("A descrição do Enum não pode ser nula.");
		}

		for (EnumIdentificavel valorEnumerado : valoresEnumerados) {
			if (descricao.equals(valorEnumerado.getDescricao())) {
				return valorEnumerado;
			}
		}

		throw new IllegalArgumentException("A descrição do Enum é inexistente.");
	}

}
