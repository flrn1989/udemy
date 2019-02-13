/**
 * 
 */
package udemy.curso.excecoes.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/** ExcecaoRest */
@JsonIgnoreProperties(value = { "cause", "stackTrace", "suppressed", "localizedMessage" })
public abstract class ExcecaoRest extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final LocalDateTime momento = LocalDateTime.now();

	/** @return HttpStatus referente. */
	@JsonProperty
	public abstract int httpStatus();

	/** @return rótulo do momento da exceção. */
	@JsonProperty(access = Access.READ_ONLY, value = "momento")
	private String rotuloDoMomento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");
		return this.momento.format(formatter);
	}

	/** {@inheritDoc} */
	@Override
	@JsonProperty(value = "log")
	public String toString() {
		return String.join(" - ",
				"Erro",
				rotuloDoMomento(),
				String.valueOf(httpStatus()),
				getMessage());
	}

}
