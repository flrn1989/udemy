/**
 * 
 */
package udemy.curso.excecoes.rest;

import org.springframework.http.HttpStatus;

/** Exceção para representar DataIntegrityViolationException. */
public class ExcecaoDeIntegridadeDeDados extends ExcecaoRest {

	private static final long serialVersionUID = 1L;

	/** Construtor.
	 * 
	 * @param cause */
	public ExcecaoDeIntegridadeDeDados(Throwable cause) {
		this.initCause(cause);
	}

	@Override
	public HttpStatus httpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "O objeto não pôde ser manipulado por possuir vínculos no banco de dados.";
	}

}
