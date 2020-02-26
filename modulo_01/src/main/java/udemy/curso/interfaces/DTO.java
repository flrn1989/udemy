package udemy.curso.interfaces;

import java.io.Serializable;

public interface DTO<D extends Dominio> extends Serializable {

	D paraDominio();

	D preencher(D dominio);

}
