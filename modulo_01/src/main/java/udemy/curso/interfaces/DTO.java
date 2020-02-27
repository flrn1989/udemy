package udemy.curso.interfaces;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.lang3.math.NumberUtils;

public interface DTO<D extends Dominio> extends Serializable {

	@SuppressWarnings("unchecked")
	default D paraDominio() throws ReflectiveOperationException {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass()
				.getGenericInterfaces()[NumberUtils.INTEGER_ZERO];
		Class<D> dominio = (Class<D>) parameterizedType
				.getActualTypeArguments()[NumberUtils.INTEGER_ZERO];
		return preencher(dominio.newInstance());
	}

	D preencher(D dominio) throws ReflectiveOperationException;

}
