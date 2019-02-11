/**
 * 
 */
package udemy.curso.excecoes.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

/** ExcecaoRest */
public abstract class ExcecaoRest extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final LocalDateTime momento = LocalDateTime.now();

	/** @return HttpStatus referente. */
	public abstract HttpStatus httpStatus();

	/** @return rótulo do momento da exceção. */
	private String rotuloDoMomento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");
		return this.momento.format(formatter);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.join(" - ",
				"Erro",
				rotuloDoMomento(),
				String.valueOf(httpStatus().value()),
				getMessage());
	}

}
