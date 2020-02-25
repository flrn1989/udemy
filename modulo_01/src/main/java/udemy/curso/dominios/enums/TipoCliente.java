package udemy.curso.dominios.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import udemy.curso.interfaces.EnumIdentificavel;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoCliente implements EnumIdentificavel {

	PF(1, "Pessoa Física"),
	PJ(2, "Pessoa Jurídica");

	private Integer id;
	private String descricao;

}
