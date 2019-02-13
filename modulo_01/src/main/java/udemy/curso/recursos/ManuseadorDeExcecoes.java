/**
 * 
 */
package udemy.curso.recursos;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import udemy.curso.excecoes.rest.ExcecaoDeArgumentoInvalido;
import udemy.curso.excecoes.rest.ExcecaoRest;

/** ManuseadorDeExcecoes */
@ControllerAdvice
public final class ManuseadorDeExcecoes {

	/** @param e
	 * @param servletRequest
	 * @return ResponseEntity com a exceção ocorrida. */
	@ExceptionHandler
	public ResponseEntity<ExcecaoRest> excecaoRest(
			ExcecaoRest e,
			HttpServletRequest servletRequest) {

		return ResponseEntity.status(e.httpStatus()).body(e);
	}

	/** @param e
	 * @param servletRequest
	 * @return ResponseEntity com a exceção ocorrida. */
	@ExceptionHandler
	public ResponseEntity<ExcecaoDeArgumentoInvalido> excecaoDeArgumentoInvalido(
			MethodArgumentNotValidException e,
			HttpServletRequest servletRequest) {

		ExcecaoDeArgumentoInvalido excecaoTratada = new ExcecaoDeArgumentoInvalido(e);
		return ResponseEntity.status(excecaoTratada.httpStatus()).body(excecaoTratada);
	}

}
