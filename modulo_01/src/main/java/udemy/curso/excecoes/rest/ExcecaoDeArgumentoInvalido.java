/**
 * 
 */
package udemy.curso.excecoes.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Exceção para argumento inválido em requisição. Tratadora da exceção
 * <code>org.springframework.web.bind.MethodArgumentNotValidException</code>. */
public class ExcecaoDeArgumentoInvalido extends ExcecaoRest {

	private static final long serialVersionUID = 1L;

	private MethodArgumentNotValidException methodArgumentNotValidException;

	/** @param cause */
	public ExcecaoDeArgumentoInvalido(MethodArgumentNotValidException cause) {
		this.methodArgumentNotValidException = cause;
	}

	/** {@inheritDoc} */
	@Override
	public int httpStatus() {
		return HttpStatus.BAD_REQUEST.value();
	}

	/** {@inheritDoc} */
	@Override
	public String getMessage() {
		return "Argumentos inválidos na requisição.";
	}

	/** @return Lista de campos com seus respectivos erros de argumento. */
	@JsonProperty
	public List<String> camposComErro() {

		List<String> camposComErro = new ArrayList<>();

		for (FieldError erro : this.methodArgumentNotValidException
				.getBindingResult()
				.getFieldErrors()) {

			camposComErro.add(String.join(
					" - ",
					erro.getField(),
					erro.getDefaultMessage()));
		}

		return camposComErro;
	}

}
