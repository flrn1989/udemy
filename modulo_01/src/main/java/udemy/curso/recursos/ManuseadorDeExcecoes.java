/**
 * 
 */
package udemy.curso.recursos;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import udemy.curso.excecoes.rest.ExcecaoRest;

/** ManuseadorDeExcecoes */
@ControllerAdvice
public final class ManuseadorDeExcecoes {

	/** @param e
	 * @param servletRequest
	 * @return ResponseEntity com a exceção ocorrida. */
	@ExceptionHandler
	public ResponseEntity<String> excecaoRest(ExcecaoRest e, HttpServletRequest servletRequest) {
		return ResponseEntity.status(e.httpStatus().value()).body(e.toString());
	}

}
