package udemy.curso.dominios.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import udemy.curso.interfaces.EnumIdentificavel;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EstadoDePagamento implements EnumIdentificavel {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private Integer id;
	private String descricao;

}
